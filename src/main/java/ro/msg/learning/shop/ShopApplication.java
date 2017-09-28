package ro.msg.learning.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * */

@SpringBootApplication
public class ShopApplication {

    @Autowired
    public ShopApplication(JdbcTemplate jdbcTemplate) {

        jdbcTemplate.execute("DROP TABLE jCategory IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE jCategory(" +
            "id INTEGER IDENTITY(1,1) PRIMARY KEY NOT NULL," +
            "name VARCHAR(255)," +
            "primaryCategoryId INTEGER," +
            "CONSTRAINT CatToCat FOREIGN KEY (primaryCategoryId) REFERENCES jCategory(id))");

        jdbcTemplate.execute("DROP TABLE jProducts IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE jProducts(" +
            "id INTEGER IDENTITY(1,1) PRIMARY KEY NOT NULL," +
            "name VARCHAR(255)," +
            "categoryID INTEGER," +
            "CONSTRAINT ProdToCat FOREIGN KEY (categoryID) REFERENCES jCategory(id))");

    }

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
