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
public class CategoryJDBC {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ProductCategory create(final ProductCategory productCategory) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO jCategory(name,primaryCategoryId) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, productCategory.getName());
            if (productCategory.getMainCategory() != null) {
                ps.setLong(2, productCategory.getMainCategory().getId());
            } else {
                ps.setObject(2, null);
            }
            return ps;
        }, holder);

        int newUserId = holder.getKey().intValue();
        productCategory.setId(newUserId);
        return productCategory;
    }

    //TODO findAll for CategoryJDBC
    @Transactional(readOnly = true)
    public List<ProductCategory> findAll() {
        return jdbcTemplate.query("SELECT * FROM jCategory"
            , (rs, rowNum) -> new ProductCategory(rs.getString("name")));
    }

    //TODO findById for CategoryJDBC
    @Transactional(readOnly = true)
    public ProductCategory findProductCategoryById(long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM jCategory WHERE id=?",
            new Object[]{id}, (rs, rowNum) -> {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(rs.getLong("id"));
                if (rs.getObject("primaryCategoryId") != null) {
                    productCategory.setMainCategory(this.findProductCategoryById(rs.getLong("primaryCategoryId")));
                }
                productCategory.setName(rs.getString("name"));

                productCategory.setProducts(jdbcTemplate.query("SELECT * FROM jProducts WHERE CATEGORYID  = ?", new Object[]{id}, (rs2, rowNum2) -> new Product(rs2.getString("name"))));
                return productCategory;
            });
    }
}
