package ro.msg.learning.shop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.ProductCategory;

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
        jdbcTemplate.update("INSERT INTO jCategory(id,name,primaryCategoryId) VALUES(?,?,?)", productCategory.getId(), productCategory.getName(), productCategory.getMainCategoryId());
        return productCategory;
    }

    @Transactional(readOnly = true)
    public List<ProductCategory> findAll() {
        return jdbcTemplate.query("SELECT * FROM jCategory"
            , (rs, rowNum) -> findById(rs.getLong("id")));
    }

    @Transactional(readOnly = true)
    public ProductCategory findById(long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM jCategory WHERE id=?",
            new Object[]{id}, (rs, rowNum) -> new ProductCategory(id, rs.getString("name"), rs.getLong("primaryCategoryId"))
        );
    }
}
