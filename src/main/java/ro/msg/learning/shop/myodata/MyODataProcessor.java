package ro.msg.learning.shop.myodata;

import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmLiteralKind;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static ro.msg.learning.shop.myodata.MyODataEdmProvider.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

public class MyODataProcessor extends ODataSingleProcessor {

    private final MyODataStorage dataStore;

    public MyODataProcessor(MyODataStorage dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType) throws ODataException {

        EdmEntitySet entitySet;

        if (uriInfo.getNavigationSegments().isEmpty()) {
            entitySet = uriInfo.getStartEntitySet();

            if (ENTITY_SET_NAME_EMPLOYEE.equals(entitySet.getName())) {
                return EntityProvider
                    .writeFeed(contentType, entitySet, dataStore.getEmployeeSet(), EntityProviderWriteProperties
                        .serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
            } else if (ENTITY_SET_NAME_ORDER.equals(entitySet.getName())) {
                return EntityProvider
                    .writeFeed(contentType, entitySet, dataStore.getOrderSet(), EntityProviderWriteProperties
                        .serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
            } else if (ENTITY_SET_NAME_ORDER_DETAILS.equals(entitySet.getName())) {
                return EntityProvider
                    .writeFeed(contentType, entitySet, dataStore.getOrderDetailsSet(), EntityProviderWriteProperties
                        .serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
            } else if (ENTITY_SET_NAME_PRODUCT.equals(entitySet.getName())) {
                return EntityProvider
                    .writeFeed(contentType, entitySet, dataStore.getProductSet(), EntityProviderWriteProperties
                        .serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
            } else if (ENTITY_SET_NAME_PRODUCT_CATEGORY.equals(entitySet.getName())) {
                return EntityProvider
                    .writeFeed(contentType, entitySet, dataStore.getProductCategorySet(), EntityProviderWriteProperties
                        .serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
            }

            throw new ODataNotFoundException(ODataNotFoundException.ENTITY);

        } else if (uriInfo.getNavigationSegments().size() == 1) {
            EdmEntitySet startSet = uriInfo.getStartEntitySet();
            entitySet = uriInfo.getTargetEntitySet();
            List<Map<String, Object>> dataList = null;

            if (ENTITY_SET_NAME_EMPLOYEE.equals(startSet.getName())) {

                if (ENTITY_SET_NAME_ORDER.equals(entitySet.getName())) {
                    long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                    dataList = dataStore.getOrderSetForEmployee(id);
                }

            } else if (ENTITY_SET_NAME_ORDER.equals(startSet.getName())) {

                if (ENTITY_SET_NAME_ORDER_DETAILS.equals(entitySet.getName())) {
                    long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                    dataList = dataStore.getOrderDetailsSetForOrder(id);
                }

            } else if (ENTITY_SET_NAME_PRODUCT_CATEGORY.equals(startSet.getName())) {

                if (ENTITY_SET_NAME_PRODUCT.equals(entitySet.getName())) {
                    long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                    dataList = dataStore.getProductSetForProductCategory(id);
                }

            }

            if (dataList != null) {
                return EntityProvider.writeFeed(contentType, entitySet, dataList, EntityProviderWriteProperties
                    .serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
            }
            throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
        }

        throw new ODataNotImplementedException();
    }

    @Override
    public ODataResponse readEntity(GetEntityUriInfo uriInfo, String contentType) throws ODataException {

        if (uriInfo.getNavigationSegments().isEmpty()) {
            EdmEntitySet entitySet = uriInfo.getStartEntitySet();
            Map<String, Object> data = null;

            if (ENTITY_SET_NAME_EMPLOYEE.equals(entitySet.getName())) {
                long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                data = dataStore.getEmployee(id);

            } else if (ENTITY_SET_NAME_ORDER.equals(entitySet.getName())) {
                long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                data = dataStore.getOrder(id);

            } else if (ENTITY_SET_NAME_ORDER_DETAILS.equals(entitySet.getName())) {
                long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                data = dataStore.getOrderDetails(id);

            } else if (ENTITY_SET_NAME_PRODUCT.equals(entitySet.getName())) {
                long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                data = dataStore.getProduct(id);

            } else if (ENTITY_SET_NAME_PRODUCT_CATEGORY.equals(entitySet.getName())) {
                long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                data = dataStore.getProductCategory(id);

            }

            if (data != null) {
                URI serviceRoot = getContext().getPathInfo().getServiceRoot();
                EntityProviderWriteProperties.ODataEntityProviderPropertiesBuilder propertiesBuilder =
                    EntityProviderWriteProperties.serviceRoot(serviceRoot);

                return EntityProvider.writeEntry(contentType, entitySet, data, propertiesBuilder.build());
            }

            throw new ODataNotFoundException(ODataNotFoundException.ENTITY);

        } else if (uriInfo.getNavigationSegments().size() == 1) {
            EdmEntitySet startSet = uriInfo.getStartEntitySet();
            EdmEntitySet entitySet = uriInfo.getTargetEntitySet();

            Map<String, Object> data = null;

            if (ENTITY_SET_NAME_ORDER.equals(startSet.getName())) {

                if (ENTITY_SET_NAME_EMPLOYEE.equals(entitySet.getName())) {
                    long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                    data = dataStore.getEmployeeForOrder(id);
                }

            } else if (ENTITY_SET_NAME_ORDER_DETAILS.equals(startSet.getName())) {

                if (ENTITY_SET_NAME_ORDER.equals(entitySet.getName())) {
                    long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                    data = dataStore.getOrderForOrderDetails(id);
                } else if (ENTITY_SET_NAME_PRODUCT.equals(entitySet.getName())) {
                    long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                    data = dataStore.getProductForOrderDetails(id);
                }


            } else if (ENTITY_SET_NAME_PRODUCT.equals(startSet.getName())) {

                if (ENTITY_SET_NAME_PRODUCT_CATEGORY.equals(entitySet.getName())) {
                    long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                    data = dataStore.getProductCategoryForProduct(id);
                }


            } else if (ENTITY_SET_NAME_PRODUCT_CATEGORY.equals(startSet.getName())) {

                if (ENTITY_SET_NAME_PRODUCT_CATEGORY.equals(entitySet.getName())) {
                    long id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                    data = dataStore.getProductCategoryForProductCategory(id);
                    if (data != null && data.isEmpty()) {
                        return ODataResponse.status(HttpStatusCodes.fromStatusCode(204)).build();
                    }
                }


            }

            if (data != null) {
                return EntityProvider.writeEntry(contentType, uriInfo.getTargetEntitySet(),
                    data, EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot())
                        .build());
            }

            throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
        }

        throw new ODataNotImplementedException();
    }

    private int getKeyValue(KeyPredicate key) throws ODataException {
        EdmProperty property = key.getProperty();
        EdmSimpleType type = (EdmSimpleType) property.getType();
        return type.valueOfString(key.getLiteral(), EdmLiteralKind.DEFAULT, property.getFacets(), Integer.class);
    }

}
