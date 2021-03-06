package ro.msg.learning.shop.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findByNameTest() {
        repository.findAll().forEach(x -> assertSame("Test " + x.getName(), x, repository.findByName(x.getName())));
        assertTrue("Not empty", repository.findAll().size() > 0);
    }
}
