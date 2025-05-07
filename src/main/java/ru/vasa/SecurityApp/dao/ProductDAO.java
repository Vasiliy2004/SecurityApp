package ru.vasa.SecurityApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasa.SecurityApp.models.Product;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void update(int id, Product product) {

        jdbcTemplate.update("UPDATE Products SET name=?, category_id=?, price=?, quantity=?, supplier_id=? WHERE id=?",
                product.getName(),
                product.getCategory_id(),
                product.getPrice(),
                product.getQuantity(),
                product.getSupplier_id(),
                id);

    }

    public List<Product> index() {
        return jdbcTemplate.query("SELECT * FROM Products", new BeanPropertyRowMapper<>(Product.class));
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE  FROM Products WHERE id=?",id);
    }

    public Optional<Product> getProductByName(String fullname) {
        return jdbcTemplate.query("SELECT * FROM Product WHERE name=?",
                new Object[]{fullname},
                new BeanPropertyRowMapper<>(Product.class)).stream().findAny();
    }


    public Product show(int id) {

        return jdbcTemplate.query("SELECT * FROM Products WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Product.class))
                .stream().findAny().orElse(null);

    }


    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO Products (name, category_id,price,quantity,supplier_id) VALUES(?,?,?,?,?)",
                product.getName(),
                product.getCategory_id(),
                product.getPrice(),
                product.getQuantity(),
                product.getSupplier_id());
    }
}
