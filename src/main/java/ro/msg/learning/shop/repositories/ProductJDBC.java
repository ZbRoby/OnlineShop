package ro.msg.learning.shop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Product;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into jProducts(name,categoryID) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            if (product.getCategory() != null) {
                ps.setLong(2, product.getCategory().getId());
            } else {
                ps.setObject(2, null);
            }
            return ps;
        }, holder);
        int newId = holder.getKey().intValue();
        product.setId(newId);
        return product;

    }

    //TODO findAll for ProductJDBC
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from jProducts"
            , (rs, rowNum) -> new Product(rs.getString("name")));
    }


    //TODO findById for ProductJDBC
    @Transactional(readOnly = true)
    public Product findById(long id) {
        return jdbcTemplate.queryForObject(
            "select * from jProducts where id=?",
            new Object[]{id}, (rs, rowNum) -> new Product(rs.getString("name")));
    }
}


