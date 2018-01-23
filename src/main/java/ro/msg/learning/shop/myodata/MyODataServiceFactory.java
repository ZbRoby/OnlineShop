package ro.msg.learning.shop.myodata;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Component
public class MyODataServiceFactory extends ODataServiceFactory {

    private final EdmProvider edmProvider;
    private final MyODataStorage dataStore;

    @Autowired
    public MyODataServiceFactory(EdmProvider edmProvider, MyODataStorage dataStore) {
        this.edmProvider = edmProvider;
        this.dataStore = dataStore;
    }

    @Override
    public ODataService createService(ODataContext ctx) throws ODataException {
        return createODataSingleProcessorService(edmProvider, new MyODataProcessor(dataStore));
    }

}
