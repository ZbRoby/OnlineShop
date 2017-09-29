package ro.msg.learning.shop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;

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

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from jProducts"
            , (rs, rowNum) -> findById(rs.getLong("id")));
    }

    @Transactional(readOnly = true)
    public Product findById(long id) {
        return jdbcTemplate.queryForObject(
            "select * from jProducts where id=?",
            new Object[]{id}, (rs, rowNum) -> {
                Product product = new Product();
                product.setId(id);
                product.setName(rs.getString("name"));
                Integer temp = rs.getInt("categoryId");
                if (temp != 0) {
                    product.setCategory(
                        jdbcTemplate.queryForObject("select * from jCategory where id=?",
                            new Object[]{temp}, (rs2, rowNum2) -> {
                                ProductCategory productCategory = new ProductCategory();
                                productCategory.setId(rs2.getInt("id"));
                                productCategory.setName(rs2.getString("name"));
                                return productCategory;
                            })
                    );
                }
                return product;
            });

    }
}


