package ro.msg.learning.shop.utils;

import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.models.LocationDistance;
import ro.msg.learning.shop.models.gdistance.GoogleDistance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class GoogleDistanceCalculator implements DistanceCalculator {

    private final RestTemplate restTemplate;
    private final String distanceApiKey;
    private Address destination;
    private int splitNr;
    private String baseUrl;

    public GoogleDistanceCalculator(RestTemplate restTemplate, String distanceApiKey, String baseUrl, int splitNr) {
        this.restTemplate = restTemplate;
        this.distanceApiKey = distanceApiKey;
        this.splitNr = splitNr;
        this.baseUrl = baseUrl;
    }

    private String getCityAndCountry(Address productsLocations) {
        StringBuilder ret = new StringBuilder();
        String country = productsLocations.getCountry();
        String city = productsLocations.getCity();
        ret.append(city).append(",").append(country);
        return ret.toString();
    }

    private String getUrl(List<ProductsLocations> origins) {
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/json?");
        stringBuilder.append("key=").append(distanceApiKey);
        stringBuilder.append("&origins=");
        for (ProductsLocations origin : origins) {
            stringBuilder.append(getCityAndCountry(origin.getLocation().getAddress())).append("|");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("&destinations=").append(getCityAndCountry(destination));
        return stringBuilder.toString();
    }

    @Override
    public List<ProductsLocations> sortLocations(Address destination, List<ProductsLocations> origins) {
        this.destination = destination;
        List<LocationDistance> locationDistances = new ArrayList<>();
        List<ProductsLocations> others = new ArrayList<>();
        for (int i = 0; i <= origins.size() / splitNr; i++) {
            int firstIndex = i * splitNr;
            int lastIndex = firstIndex + splitNr;
            lastIndex = (lastIndex > origins.size()) ? origins.size() : lastIndex;
            getLists(origins.subList(firstIndex, lastIndex), locationDistances, others);

        }
        locationDistances.sort(Comparator.comparingDouble(o -> o.getDistance().getDistance().getValue()));
        List<ProductsLocations> returnList = locationDistances.stream().map(LocationDistance::getLocation).collect(Collectors.toList());
        returnList.addAll(others);
        return returnList;
    }

    private void getLists(List<ProductsLocations> origins, List<LocationDistance> locationDistances, List<ProductsLocations> others) {
        if (!origins.isEmpty()) {
            String url = getUrl(origins);
            Logger.getGlobal().info(url);
            GoogleDistance googleDistance = restTemplate.getForObject(url, GoogleDistance.class);
            for (int i = 0; i < origins.size(); i++)
                switch (googleDistance.getRows().get(i).getElements().get(0).getStatus()) {
                    case OK:
                        locationDistances.add(new LocationDistance(origins.get(i), googleDistance.getRows().get(i).getElements().get(0)));
                        break;
                    case MAX_ROUTE_LENGTH_EXCEEDED:
                    case ZERO_RESULTS:
                        others.add(0, origins.get(i));
                        break;
                    default:
                        others.add(origins.get(i));
                        break;
                }
        }
    }

}
