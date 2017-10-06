package ro.msg.learning.shop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Product;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public class ProductJDBC {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product create(final Product product) {
        jdbcTemplate.update("insert into jProducts(id,name,categoryID) values(?,?,?)", product.getId(), product.getName(), product.getCategoryId());
        return product;

    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from jProducts"
            , (rs, rowNum) -> findById(rs.getLong("id")));
    }

    @Transactional(readOnly = true)
    public Product findById(long id) {
        return jdbcTemplate.queryForObject(
            "select * from jProducts where id=?",
            new Object[]{id}, (rs, rowNum) -> new Product(id, rs.getString("name"), rs.getLong("categoryId"))
        );

    }
}


