package ro.msg.learning.shop.services.strategies;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.models.LocationDistance;
import ro.msg.learning.shop.models.gdistance.GoogleDistance;
import ro.msg.learning.shop.repositories.CustomerRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class ProximityQS implements QuantityStrategy {

    private final CustomerRepository customerRepository;
    private final String apiKey;
    private final RestTemplate restTemplate;
    private Customer customer;

    public ProximityQS(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.apiKey = setApiKey();
        restTemplate = setRestTemplate();
    }

    /**
     * returns the apiKey from the file googleApi.txt, if it does not find the file then returns the String 'Non-Existent'
     */
    private String setApiKey() {
        String temp;
        try (FileReader fileReader = new FileReader("googleApi.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            temp = bufferedReader.readLine();
        } catch (Exception e) {
            temp = "Non-Existent";
        }
        return temp;
    }

    /**
     * returns the Rest Template with proxy if it finds the file proxy.txt
     * the file proxy.txt has on the first line the name and on the second line the port
     */
    private RestTemplate setRestTemplate() {
        String proxyName;
        int proxyNr;
        RestTemplate temp;

        try (FileReader fileReader = new FileReader("proxy.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            proxyName = bufferedReader.readLine();
            proxyNr = Integer.valueOf(bufferedReader.readLine());
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyName, proxyNr)));
            temp = new RestTemplate(requestFactory);
        } catch (Exception e) {
            temp = new RestTemplate();
        }

        return temp;
    }

    private String getCityAndCountry(Address productsLocations) {
        StringBuilder ret = new StringBuilder();
        String country = productsLocations.getCountry();
        String city = productsLocations.getCity();
        ret.append(city).append(",").append(country);
        return ret.toString();
    }

    private String getUrl(ProductsLocations locationAndProduct) {
        return "https://maps.googleapis.com/maps/api/distancematrix/json?" +
            "key=" + apiKey +
            "&destinations=" +
            getCityAndCountry(locationAndProduct.getLocation().getAddress()) +
            "&origins=" + getCityAndCountry(customer.getAddress());
    }

    private List<ProductsLocations> sort(List<ProductsLocations> locationAndProduct) {
        List<LocationDistance> locationDistances = new ArrayList<>();
        List<ProductsLocations> others = new ArrayList<>();
        for (ProductsLocations locations : locationAndProduct) {
            String url = getUrl(locations);
            GoogleDistance googleDistance = restTemplate.getForObject(url, GoogleDistance.class);
            switch (googleDistance.getRows().get(0).getElements().get(0).getStatus()) {
                case OK:
                    locationDistances.add(new LocationDistance(locations, googleDistance.getRows().get(0).getElements().get(0)));
                    break;
                case MAX_ROUTE_LENGTH_EXCEEDED:
                case ZERO_RESULTS:
                    others.add(0, locations);
                    break;
                default:
                    others.add(locations);
                    break;
            }
        }
        locationDistances.sort(Comparator.comparingDouble(o -> o.getDistance().getDistance().getValue()));
        List<ProductsLocations> returnList = locationDistances.stream().map(LocationDistance::getLocation).collect(Collectors.toList());
        returnList.addAll(others);
        return returnList;
    }

    private void setCustomer() {
        Optional<Customer> customerOptional;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            customerOptional = customerRepository.findOptionalByUserUsername(authentication.getName());
        } else {
            customerOptional = customerRepository.findOptionalByUserUsername("anyString");
        }
        customerOptional.ifPresent(c -> customer = c);
    }

    @Override
    public List<ProductsLocations> getLocations
        (Map<Long, Long> productList, List<ProductsLocations> locationAndProduct) {
        setCustomer();
        return new GreedyQS().getLocations(productList, sort(locationAndProduct));
    }


}
