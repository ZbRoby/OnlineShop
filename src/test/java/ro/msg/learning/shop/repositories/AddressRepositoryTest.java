package ro.msg.learning.shop.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Address;

import static org.junit.Assert.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressRepository repository;

    @Test
    public void findAllByCityTest() {
        String city1 = "CityOne";
        String city2 = "CityMultiple";
        String city3 = "CityNone";
        long count = repository.count();

        repository.save(new Address("Test1", city1, "Test1", "Test1", null));
        entityManager.persist(new Address("Test2", city2, "Test2", "Test2", null));
        repository.save(new Address("Test3", city2, "Test3", "Test3", null));
        entityManager.persist(new Address("Test4", city2, "Test4", "Test4", null));
        repository.save(new Address("Test5", "CityOther5", "Test5", "Test5", null));
        entityManager.persist(new Address("Test6", "CityOther6", "Test6", "Test6", null));

        assertTrue("Test City:[" + city1 + "] True", this.repository.findAllByCity(city1).stream().allMatch(address -> address.getCity().equals(city1)));
        assertTrue("Test City:[" + city2 + "] True", this.repository.findAllByCity(city2).stream().allMatch(address -> address.getCity().equals(city2)));
        assertTrue("Test City:[" + city3 + "] True", this.repository.findAllByCity(city3).stream().allMatch(address -> address.getCity().equals(city3)));
        assertFalse("Test City:[" + city1 + "] False", this.repository.findAllByCity(city1).stream().anyMatch(address -> !address.getCity().equals(city1)));
        assertFalse("Test City:[" + city2 + "] False", this.repository.findAllByCity(city2).stream().anyMatch(address -> !address.getCity().equals(city2)));
        assertFalse("Test City:[" + city3 + "] False", this.repository.findAllByCity(city3).stream().anyMatch(address -> !address.getCity().equals(city3)));

        repository.findAllByCity(city1).forEach(repository::delete);
        repository.findAllByCity(city2).forEach(repository::delete);
        repository.findAllByCity("CityOther5").forEach(repository::delete);
        repository.findAllByCity("CityOther6").forEach(repository::delete);

        assertTrue("Same nr of addresses; expect:" + count + " got:" + repository.count(), repository.count() == count);
    }

    @Test
    public void findAllByCountryTest() {
        String country1 = "CountryOne";
        String country2 = "CountryMultiple";
        String country3 = "CountryNone";
        long count = repository.count();

        repository.save(new Address(country1, "Test1", "Test1", "Test1", null));
        entityManager.persist(new Address(country2, "Test2", "Test2", "Test2", null));
        repository.save(new Address(country2, "Test3", "Test3", "Test3", null));
        entityManager.persist(new Address(country2, "Test4", "Test4", "Test4", null));
        repository.save(new Address("CountryOther1", "Test5", "Test5", "Test5", null));
        entityManager.persist(new Address("CountryOther2", "Test6", "Test6", "Test6", null));

        assertTrue("Test Country:[" + country1 + "] True", this.repository.findAllByCountry(country1).stream().allMatch(address -> address.getCountry().equals(country1)));
        assertTrue("Test Country:[" + country2 + "] True", this.repository.findAllByCountry(country2).stream().allMatch(address -> address.getCountry().equals(country2)));
        assertTrue("Test Country:[" + country3 + "] True", this.repository.findAllByCountry(country3).stream().allMatch(address -> address.getCountry().equals(country3)));
        assertFalse("Test Country:[" + country1 + "] False", this.repository.findAllByCountry(country1).stream().anyMatch(address -> !address.getCountry().equals(country1)));
        assertFalse("Test Country:[" + country2 + "] False", this.repository.findAllByCountry(country2).stream().anyMatch(address -> !address.getCountry().equals(country2)));
        assertFalse("Test Country:[" + country3 + "] False", this.repository.findAllByCountry(country3).stream().anyMatch(address -> !address.getCountry().equals(country3)));

        repository.findAllByCountry(country1).forEach(repository::delete);
        repository.findAllByCountry(country2).forEach(repository::delete);
        repository.findAllByCountry("CountryOther1").forEach(repository::delete);
        repository.findAllByCountry("CountryOther2").forEach(repository::delete);

        assertTrue("Same nr of addresses; expect:" + count + " got:" + repository.count(), repository.count() == count);
    }


    @Test
    public void findAllByStreetTest() {
        String street1 = "StreetOne";
        String street2 = "StreetMultiple";
        String street3 = "StreetNone";
        long count = repository.count();

        repository.save(new Address("Test1", "Test1", street1, "Test1", null));
        entityManager.persist(new Address("Test2", "Test2", street2, "Test2", null));
        repository.save(new Address("Test3", "Test3", street2, "Test3", null));
        entityManager.persist(new Address("Test4", "Test4", street2, "Test4", null));
        repository.save(new Address("Test5", "Test5", "StreetOther1", "Test5", null));
        entityManager.persist(new Address("Test6", "Test6", "StreetOther2", "Test6", null));

        assertTrue("Test Street:[" + street1 + "] True", this.repository.findAllByStreet(street1).stream().allMatch(address -> address.getStreet().equals(street1)));
        assertTrue("Test Street:[" + street2 + "] True", this.repository.findAllByStreet(street2).stream().allMatch(address -> address.getStreet().equals(street2)));
        assertTrue("Test Street:[" + street3 + "] True", this.repository.findAllByStreet(street3).stream().allMatch(address -> address.getStreet().equals(street3)));
        assertFalse("Test Street:[" + street1 + "] False", this.repository.findAllByStreet(street1).stream().anyMatch(address -> !address.getStreet().equals(street1)));
        assertFalse("Test Street:[" + street2 + "] False", this.repository.findAllByStreet(street2).stream().anyMatch(address -> !address.getStreet().equals(street2)));
        assertFalse("Test Street:[" + street3 + "] False", this.repository.findAllByStreet(street3).stream().anyMatch(address -> !address.getStreet().equals(street3)));

        repository.findAllByStreet(street1).forEach(repository::delete);
        repository.findAllByStreet(street2).forEach(repository::delete);
        repository.findAllByStreet("StreetOther1").forEach(repository::delete);
        repository.findAllByStreet("StreetOther2").forEach(repository::delete);

        assertTrue("Same nr of addresses; expect:" + count + " got:" + repository.count(), repository.count() == count);
    }

    @Test
    public void findAllByZipCodeTest() {
        String zipCode1 = "ZipCodeOne";
        String zipCode2 = "ZipCodeMultiple";
        String zipCode3 = "ZipCodeNone";
        long count = repository.count();

        repository.save(new Address("Test1", "Test1", "Test1", zipCode1, null));
        entityManager.persist(new Address("Test2", "Test2", "Test2", zipCode2, null));
        repository.save(new Address("Test3", "Test3", "Test3", zipCode2, null));
        entityManager.persist(new Address("Test4", "Test4", "Test4", zipCode2, null));
        repository.save(new Address("Test5", "Test5", "Test5", "ZipCodeOther1", null));
        entityManager.persist(new Address("Test6", "Test6", "Test6", "ZipCodeOther2", null));

        assertTrue("Test ZipCode:[" + zipCode1 + "] True", this.repository.findAllByZipCode(zipCode1).stream().allMatch(address -> address.getZipCode().equals(zipCode1)));
        assertTrue("Test ZipCode:[" + zipCode2 + "] True", this.repository.findAllByZipCode(zipCode2).stream().allMatch(address -> address.getZipCode().equals(zipCode2)));
        assertTrue("Test ZipCode:[" + zipCode3 + "] True", this.repository.findAllByZipCode(zipCode3).stream().allMatch(address -> address.getZipCode().equals(zipCode3)));
        assertFalse("Test ZipCode:[" + zipCode1 + "] False", this.repository.findAllByZipCode(zipCode1).stream().anyMatch(address -> !address.getZipCode().equals(zipCode1)));
        assertFalse("Test ZipCode:[" + zipCode2 + "] False", this.repository.findAllByZipCode(zipCode2).stream().anyMatch(address -> !address.getZipCode().equals(zipCode2)));
        assertFalse("Test ZipCode:[" + zipCode3 + "] False", this.repository.findAllByZipCode(zipCode3).stream().anyMatch(address -> !address.getZipCode().equals(zipCode3)));

        repository.findAllByZipCode(zipCode1).forEach(repository::delete);
        repository.findAllByZipCode(zipCode2).forEach(repository::delete);
        repository.findAllByZipCode("ZipCodeOther1").forEach(repository::delete);
        repository.findAllByZipCode("ZipCodeOther2").forEach(repository::delete);

        assertTrue("Same nr of addresses; expect:" + count + " got:" + repository.count(), repository.count() == count);
    }

    @Test
    public void findByCountryAndCityAndStreetTest() {
        String country1 = "CountryOne";
        String country2 = "CountryMultiple";
        String country3 = "CountryNone";
        String city1 = "CityOne";
        String city2 = "CityMultiple";
        String city3 = "CityNone";
        String street1 = "StreetOne";
        String street2 = "StreetMultiple";
        String street3 = "StreetNone";
        long count = repository.count();

        repository.save(new Address(country1, city1, street1, "Test1", null));
        entityManager.persist(new Address(country2, city2, street2, "Test2", null));

        assertNotNull("Test CountryAndCityAndStreet NotNull save", this.repository.findByCountryAndCityAndStreet(country1, city1, street1));
        assertNotNull("Test CountryAndCityAndStreet NotNull persist", this.repository.findByCountryAndCityAndStreet(country2, city2, street2));
        assertNull("Test CountryAndCityAndStreet Null", this.repository.findByCountryAndCityAndStreet(country3, city3, street3));

        repository.delete(repository.findByCountryAndCityAndStreet(country1, city1, street1));
        repository.delete(repository.findByCountryAndCityAndStreet(country2, city2, street2));

        assertTrue("Same nr of addresses; expect:" + count + " got:" + repository.count(), repository.count() == count);
    }

}

