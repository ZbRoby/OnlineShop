package ro.msg.learning.shop.myodata;

import org.apache.olingo.odata2.api.edm.EdmMultiplicity;
import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.*;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Component
public class MyODataEdmProvider extends EdmProvider {

    public static final String ENTITY_SET_NAME_EMPLOYEE = "EmployeeSet";
    public static final String ENTITY_SET_NAME_ORDER = "OrderSet";
    public static final String ENTITY_SET_NAME_ORDER_DETAILS = "OrderDetailsSet";
    public static final String ENTITY_SET_NAME_PRODUCT = "ProductSet";
    public static final String ENTITY_SET_NAME_PRODUCT_CATEGORY = "ProductCategorySet";

    public static final String ENTITY_NAME_EMPLOYEE = "Employee";
    public static final String ENTITY_NAME_ORDER = "Order";
    public static final String ENTITY_NAME_ORDER_DETAILS = "OrderDetails";
    public static final String ENTITY_NAME_PRODUCT = "Product";
    public static final String ENTITY_NAME_PRODUCT_CATEGORY = "ProductCategory";


    private static final String NAMESPACE = "ro.msg.learning.shop.entities";

    private static final FullQualifiedName ENTITY_TYPE_EMPLOYEE = new FullQualifiedName(NAMESPACE, ENTITY_NAME_EMPLOYEE);
    private static final FullQualifiedName ENTITY_TYPE_ORDER = new FullQualifiedName(NAMESPACE, ENTITY_NAME_ORDER);
    private static final FullQualifiedName ENTITY_TYPE_ORDER_DETAILS = new FullQualifiedName(NAMESPACE, ENTITY_NAME_ORDER_DETAILS);
    private static final FullQualifiedName ENTITY_TYPE_PRODUCT = new FullQualifiedName(NAMESPACE, ENTITY_NAME_PRODUCT);
    private static final FullQualifiedName ENTITY_TYPE_PRODUCT_CATEGORY = new FullQualifiedName(NAMESPACE, ENTITY_NAME_PRODUCT_CATEGORY);

    private static final String ROLE_EMPLOYEE_ORDER_SET = "Employee_OrderSet";
    private static final String ROLE_ORDER_EMPLOYEE = "Order_Employee";
    private static final String ROLE_ORDER_ORDER_DETAILS_SET = "Order_OrderDetailsSet";
    private static final String ROLE_ORDER_DETAILS_SET_ORDER = "OrderDetails_Order";
    private static final String ROLE_ORDER_DETAILS_PRODUCT = "OrderDetails_Product";
    private static final String ROLE_PRODUCT_ORDER_DETAILS = "Product_OrderDetails";
    private static final String ROLE_PRODUCT_CATEGORY_PRODUCT_SET = "ProductCategory_ProductSet";
    private static final String ROLE_PRODUCT_SET_PRODUCT_CATEGORY = "ProductSet_ProductCategory";
    private static final String ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY = "ProductCategory_ProductCategory";


    private static final FullQualifiedName ASSOCIATION_EMPLOYEE_ORDER = new FullQualifiedName(NAMESPACE, ROLE_EMPLOYEE_ORDER_SET + '_' + ROLE_ORDER_EMPLOYEE);
    private static final FullQualifiedName ASSOCIATION_ORDER_ORDER_DETAILS = new FullQualifiedName(NAMESPACE, ROLE_ORDER_ORDER_DETAILS_SET + '_' + ROLE_ORDER_DETAILS_SET_ORDER);
    private static final FullQualifiedName ASSOCIATION_ORDER_DETAILS_PRODUCT = new FullQualifiedName(NAMESPACE, ROLE_ORDER_DETAILS_PRODUCT);
    private static final FullQualifiedName ASSOCIATION_PRODUCT_PRODUCT_CATEGORY = new FullQualifiedName(NAMESPACE, ROLE_PRODUCT_SET_PRODUCT_CATEGORY + '_' + ROLE_PRODUCT_CATEGORY_PRODUCT_SET);
    private static final FullQualifiedName ASSOCIATION_PRODUCT_CATEGORY_PRODUCT_CATEGORY = new FullQualifiedName(NAMESPACE, ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY);

    private static final String ENTITY_CONTAINER = "ODataEntityContainer";
    private static final String ASSOCIATION_SET = "ODataAssocitationSet";

    @Override
    public List<Schema> getSchemas() throws ODataException {
        List<Schema> schemas = new ArrayList<>();

        Schema schema = new Schema();
        schema.setNamespace(NAMESPACE);

        List<EntityType> entityTypes = new ArrayList<>();
        entityTypes.add(getEntityType(ENTITY_TYPE_EMPLOYEE));
        entityTypes.add(getEntityType(ENTITY_TYPE_ORDER));
        entityTypes.add(getEntityType(ENTITY_TYPE_ORDER_DETAILS));
        entityTypes.add(getEntityType(ENTITY_TYPE_PRODUCT));
        entityTypes.add(getEntityType(ENTITY_TYPE_PRODUCT_CATEGORY));
        schema.setEntityTypes(entityTypes);

        List<Association> associations = new ArrayList<>();
        associations.add(getAssociation(ASSOCIATION_EMPLOYEE_ORDER));
        associations.add(getAssociation(ASSOCIATION_ORDER_ORDER_DETAILS));
        associations.add(getAssociation(ASSOCIATION_ORDER_DETAILS_PRODUCT));
        associations.add(getAssociation(ASSOCIATION_PRODUCT_PRODUCT_CATEGORY));
        associations.add(getAssociation(ASSOCIATION_PRODUCT_CATEGORY_PRODUCT_CATEGORY));
        schema.setAssociations(associations);

        List<EntityContainer> entityContainers = new ArrayList<>();
        EntityContainer entityContainer = new EntityContainer();
        entityContainer.setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);

        List<EntitySet> entitySets = new ArrayList<>();
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_EMPLOYEE));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_ORDER));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_ORDER_DETAILS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_PRODUCT));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_PRODUCT_CATEGORY));
        entityContainer.setEntitySets(entitySets);

        List<AssociationSet> associationSets = new ArrayList<>();
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_EMPLOYEE_ORDER, ENTITY_SET_NAME_EMPLOYEE, ROLE_EMPLOYEE_ORDER_SET));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_EMPLOYEE_ORDER, ENTITY_SET_NAME_ORDER, ROLE_ORDER_EMPLOYEE));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_ORDER_ORDER_DETAILS, ENTITY_SET_NAME_ORDER, ROLE_ORDER_ORDER_DETAILS_SET));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_ORDER_ORDER_DETAILS, ENTITY_SET_NAME_ORDER_DETAILS, ROLE_ORDER_DETAILS_SET_ORDER));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_ORDER_DETAILS_PRODUCT, ENTITY_SET_NAME_ORDER_DETAILS, ROLE_ORDER_DETAILS_PRODUCT));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_PRODUCT_PRODUCT_CATEGORY, ENTITY_SET_NAME_PRODUCT, ROLE_PRODUCT_SET_PRODUCT_CATEGORY));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_PRODUCT_PRODUCT_CATEGORY, ENTITY_SET_NAME_PRODUCT_CATEGORY, ROLE_PRODUCT_CATEGORY_PRODUCT_SET));
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_PRODUCT_CATEGORY_PRODUCT_CATEGORY, ENTITY_SET_NAME_PRODUCT_CATEGORY, ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY));
        entityContainer.setAssociationSets(associationSets);

        entityContainers.add(entityContainer);
        schema.setEntityContainers(entityContainers);

        schemas.add(schema);

        return schemas;
    }

    @Override
    public EntityType getEntityType(FullQualifiedName edmFQName) throws ODataException {
        if (NAMESPACE.equals(edmFQName.getNamespace())) {

            if (ENTITY_TYPE_EMPLOYEE.getName().equals(edmFQName.getName())) {

                //Properties
                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("FirstName").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("LastName").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("HomePhone").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Title").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));

                //Navigation Properties
                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("OrderSet")
                    .setRelationship(ASSOCIATION_EMPLOYEE_ORDER).setFromRole(ROLE_EMPLOYEE_ORDER_SET).setToRole(ROLE_ORDER_EMPLOYEE));

                //Key
                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("Id"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_EMPLOYEE.getName())
                    .setProperties(properties)
                    .setKey(key)
                    .setNavigationProperties(navigationProperties);

            } else if (ENTITY_TYPE_ORDER.getName().equals(edmFQName.getName())) {

                //Properties
                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("ShippedDate").setType(EdmSimpleTypeKind.DateTime));
                properties.add(new SimpleProperty().setName("OrderDate").setType(EdmSimpleTypeKind.DateTime).setFacets(new Facets().setNullable(false)));

                //Navigation Properties
                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("Employee")
                    .setRelationship(ASSOCIATION_EMPLOYEE_ORDER).setFromRole(ROLE_ORDER_EMPLOYEE).setToRole(ROLE_EMPLOYEE_ORDER_SET));
                navigationProperties.add(new NavigationProperty().setName("OrderDetailsSet")
                    .setRelationship(ASSOCIATION_ORDER_ORDER_DETAILS).setFromRole(ROLE_ORDER_ORDER_DETAILS_SET).setToRole(ROLE_ORDER_DETAILS_SET_ORDER));

                //Key
                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("Id"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_ORDER.getName())
                    .setProperties(properties)
                    .setKey(key)
                    .setNavigationProperties(navigationProperties);

            } else if (ENTITY_TYPE_ORDER_DETAILS.getName().equals(edmFQName.getName())) {

                //Properties
                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Quantity").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Discount").setType(EdmSimpleTypeKind.Double).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("UnitPrice").setType(EdmSimpleTypeKind.Double).setFacets(new Facets().setNullable(false)));

                //Navigation Properties
                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("Product")
                    .setRelationship(ASSOCIATION_ORDER_DETAILS_PRODUCT).setFromRole(ROLE_ORDER_DETAILS_PRODUCT).setToRole(ROLE_PRODUCT_ORDER_DETAILS));
                navigationProperties.add(new NavigationProperty().setName("Order")
                    .setRelationship(ASSOCIATION_ORDER_ORDER_DETAILS).setFromRole(ROLE_ORDER_DETAILS_SET_ORDER).setToRole(ROLE_ORDER_ORDER_DETAILS_SET));

                //Key
                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("Id"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_ORDER_DETAILS.getName())
                    .setProperties(properties)
                    .setKey(key)
                    .setNavigationProperties(navigationProperties);

            } else if (ENTITY_TYPE_PRODUCT.getName().equals(edmFQName.getName())) {

                //Properties
                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Name").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("SupplierName").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Description").setType(EdmSimpleTypeKind.String));
                properties.add(new SimpleProperty().setName("CurrencyCode").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Price").setType(EdmSimpleTypeKind.Double).setFacets(new Facets().setNullable(false)));

                //Navigation Properties
                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("ProductCategory")
                    .setRelationship(ASSOCIATION_PRODUCT_PRODUCT_CATEGORY).setFromRole(ROLE_PRODUCT_SET_PRODUCT_CATEGORY).setToRole(ROLE_PRODUCT_CATEGORY_PRODUCT_SET));

                //Key
                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("Id"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_PRODUCT.getName())
                    .setProperties(properties)
                    .setKey(key)
                    .setNavigationProperties(navigationProperties);

            } else if (ENTITY_TYPE_PRODUCT_CATEGORY.getName().equals(edmFQName.getName())) {
                //Properties
                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int64).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Name").setType(EdmSimpleTypeKind.String).setFacets(new Facets().setNullable(false)));

                //Navigation Properties
                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("ProductSet")
                    .setRelationship(ASSOCIATION_PRODUCT_PRODUCT_CATEGORY).setFromRole(ROLE_PRODUCT_CATEGORY_PRODUCT_SET).setToRole(ROLE_PRODUCT_SET_PRODUCT_CATEGORY));
                navigationProperties.add(new NavigationProperty().setName("MainProductCategory")
                    .setRelationship(ASSOCIATION_PRODUCT_CATEGORY_PRODUCT_CATEGORY).setFromRole(ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY).setToRole(ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY));


                //Key
                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("Id"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_PRODUCT_CATEGORY.getName())
                    .setProperties(properties)
                    .setKey(key)
                    .setNavigationProperties(navigationProperties);

            }
        }
        return null;
    }

    @Override
    public Association getAssociation(FullQualifiedName edmFQName) throws ODataException {
        if (NAMESPACE.equals(edmFQName.getNamespace())) {
            if (ASSOCIATION_EMPLOYEE_ORDER.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_EMPLOYEE_ORDER.getName())
                    .setEnd1(new AssociationEnd().setType(ENTITY_TYPE_EMPLOYEE).setRole(ROLE_EMPLOYEE_ORDER_SET).setMultiplicity(EdmMultiplicity.ONE))
                    .setEnd2(new AssociationEnd().setType(ENTITY_TYPE_ORDER).setRole(ROLE_ORDER_EMPLOYEE).setMultiplicity(EdmMultiplicity.MANY));
            } else if (ASSOCIATION_ORDER_ORDER_DETAILS.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_ORDER_ORDER_DETAILS.getName())
                    .setEnd1(new AssociationEnd().setType(ENTITY_TYPE_ORDER).setRole(ROLE_ORDER_ORDER_DETAILS_SET).setMultiplicity(EdmMultiplicity.ONE))
                    .setEnd2(new AssociationEnd().setType(ENTITY_TYPE_ORDER_DETAILS).setRole(ROLE_ORDER_DETAILS_SET_ORDER).setMultiplicity(EdmMultiplicity.MANY));
            } else if (ASSOCIATION_ORDER_DETAILS_PRODUCT.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_ORDER_DETAILS_PRODUCT.getName())
                    .setEnd1(new AssociationEnd().setType(ENTITY_TYPE_ORDER_DETAILS).setRole(ROLE_ORDER_DETAILS_PRODUCT).setMultiplicity(EdmMultiplicity.ONE))
                    .setEnd2(new AssociationEnd().setType(ENTITY_TYPE_PRODUCT).setRole(ROLE_PRODUCT_ORDER_DETAILS).setMultiplicity(EdmMultiplicity.ONE));
            } else if (ASSOCIATION_PRODUCT_PRODUCT_CATEGORY.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_PRODUCT_PRODUCT_CATEGORY.getName())
                    .setEnd1(new AssociationEnd().setType(ENTITY_TYPE_PRODUCT).setRole(ROLE_PRODUCT_SET_PRODUCT_CATEGORY).setMultiplicity(EdmMultiplicity.MANY))
                    .setEnd2(new AssociationEnd().setType(ENTITY_TYPE_PRODUCT_CATEGORY).setRole(ROLE_PRODUCT_CATEGORY_PRODUCT_SET).setMultiplicity(EdmMultiplicity.ONE));
            } else if (ASSOCIATION_PRODUCT_CATEGORY_PRODUCT_CATEGORY.getName().equals(edmFQName.getName())) {
                return new Association().setName(ASSOCIATION_PRODUCT_CATEGORY_PRODUCT_CATEGORY.getName())
                    .setEnd1(new AssociationEnd().setType(ENTITY_TYPE_PRODUCT_CATEGORY).setRole(ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY).setMultiplicity(EdmMultiplicity.ZERO_TO_ONE))
                    .setEnd2(new AssociationEnd().setType(ENTITY_TYPE_PRODUCT_CATEGORY).setRole(ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY).setMultiplicity(EdmMultiplicity.ZERO_TO_ONE));
            }
        }
        return null;
    }

    @Override
    public EntitySet getEntitySet(String entityContainer, String name) throws ODataException {
        if (ENTITY_CONTAINER.equals(entityContainer)) {
            if (ENTITY_SET_NAME_EMPLOYEE.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_EMPLOYEE);
            } else if (ENTITY_SET_NAME_ORDER.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_ORDER);
            } else if (ENTITY_SET_NAME_ORDER_DETAILS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_ORDER_DETAILS);
            } else if (ENTITY_SET_NAME_PRODUCT.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_PRODUCT);
            } else if (ENTITY_SET_NAME_PRODUCT_CATEGORY.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_PRODUCT_CATEGORY);
            }
        }
        return null;
    }

    @Override
    public AssociationSet getAssociationSet(String entityContainer, FullQualifiedName association, String sourceEntitySetName, String sourceEntitySetRole) throws ODataException {
        if (ENTITY_CONTAINER.equals(entityContainer)) {
            if (ASSOCIATION_EMPLOYEE_ORDER.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                    .setAssociation(ASSOCIATION_EMPLOYEE_ORDER)
                    .setEnd1(new AssociationSetEnd().setRole(ROLE_ORDER_EMPLOYEE).setEntitySet(ENTITY_SET_NAME_ORDER))
                    .setEnd2(new AssociationSetEnd().setRole(ROLE_EMPLOYEE_ORDER_SET).setEntitySet(ENTITY_SET_NAME_EMPLOYEE));
            } else if (ASSOCIATION_ORDER_ORDER_DETAILS.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                    .setAssociation(ASSOCIATION_ORDER_ORDER_DETAILS)
                    .setEnd1(new AssociationSetEnd().setRole(ROLE_ORDER_DETAILS_SET_ORDER).setEntitySet(ENTITY_SET_NAME_ORDER_DETAILS))
                    .setEnd2(new AssociationSetEnd().setRole(ROLE_ORDER_ORDER_DETAILS_SET).setEntitySet(ENTITY_SET_NAME_ORDER));
            } else if (ASSOCIATION_ORDER_DETAILS_PRODUCT.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                    .setAssociation(ASSOCIATION_ORDER_DETAILS_PRODUCT)
                    .setEnd1(new AssociationSetEnd().setRole(ROLE_PRODUCT_ORDER_DETAILS).setEntitySet(ENTITY_SET_NAME_PRODUCT))
                    .setEnd2(new AssociationSetEnd().setRole(ROLE_ORDER_DETAILS_PRODUCT).setEntitySet(ENTITY_SET_NAME_ORDER_DETAILS));
            } else if (ASSOCIATION_PRODUCT_PRODUCT_CATEGORY.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                    .setAssociation(ASSOCIATION_PRODUCT_PRODUCT_CATEGORY)
                    .setEnd1(new AssociationSetEnd().setRole(ROLE_PRODUCT_CATEGORY_PRODUCT_SET).setEntitySet(ENTITY_SET_NAME_PRODUCT_CATEGORY))
                    .setEnd2(new AssociationSetEnd().setRole(ROLE_PRODUCT_SET_PRODUCT_CATEGORY).setEntitySet(ENTITY_SET_NAME_PRODUCT));
            } else if (ASSOCIATION_PRODUCT_CATEGORY_PRODUCT_CATEGORY.equals(association)) {
                return new AssociationSet().setName(ASSOCIATION_SET)
                    .setAssociation(ASSOCIATION_PRODUCT_CATEGORY_PRODUCT_CATEGORY)
                    .setEnd1(new AssociationSetEnd().setRole(ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY).setEntitySet(ENTITY_SET_NAME_PRODUCT_CATEGORY))
                    .setEnd2(new AssociationSetEnd().setRole(ROLE_PRODUCT_CATEGORY_PRODUCT_CATEGORY).setEntitySet(ENTITY_SET_NAME_PRODUCT_CATEGORY));
            }
        }
        return null;
    }

    @Override
    public EntityContainerInfo getEntityContainerInfo(String name) throws ODataException {
        if (name == null || ENTITY_CONTAINER.equals(name)) {
            return new EntityContainerInfo().setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);
        }

        return null;
    }
}
