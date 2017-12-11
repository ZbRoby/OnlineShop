package ro.msg.learning.shop.services.strategies;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.exceptions.NoCustomerException;
import ro.msg.learning.shop.repositories.CustomerRepository;
import ro.msg.learning.shop.utils.DistanceCalculator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class ProximityQS implements QuantityStrategy {

    private final CustomerRepository customerRepository;
    private Customer customer;
    private DistanceCalculator distanceCalculator;

    public ProximityQS(CustomerRepository customerRepository, DistanceCalculator distanceCalculator) {
        this.customerRepository = customerRepository;
        this.distanceCalculator = distanceCalculator;
    }

    private void setCustomer() {
        Optional<Customer> customerOptional;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            customerOptional = customerRepository.findOptionalByUserUsername(authentication.getName());
        } else {
            customerOptional = Optional.empty();
        }
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
        } else {
            throw new NoCustomerException();
        }
    }

    @Override
    public List<ProductsLocations> getLocations
        (Map<Long, Long> productList, List<ProductsLocations> locationAndProduct) {
        setCustomer();
        return new GreedyQS().getLocations(productList, distanceCalculator.sortLocations(customer.getAddress(), locationAndProduct));
    }


}
