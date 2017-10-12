package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Address;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByCity(String city);

    List<Address> findAllByCountry(String country);

    List<Address> findAllByStreet(String street);

    List<Address> findAllByZipCode(String zipCode);

    Address findByCountryAndCityAndStreet(String country, String city, String street);
}
