package ru.vasa.SecurityApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasa.SecurityApp.models.Category;
import ru.vasa.SecurityApp.models.Product;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void update(int id, Category category) {

        jdbcTemplate.update("UPDATE Categories SET name=?, description=? WHERE id=?",
                category.getName(),
                category.getDescription(),
                id);

    }

    public List<Category> index() {
        return jdbcTemplate.query("SELECT * FROM Categories", new BeanPropertyRowMapper<>(Category.class));
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE  FROM Categories WHERE id=?",id);
    }



    public Category show(int id) {
        return jdbcTemplate.query("SELECT * FROM Categories WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Category.class))
                .stream().findAny().orElse(null);

    }


    public void save(Category product) {
        jdbcTemplate.update("INSERT INTO Categories (name, description) VALUES(?,?)",
                product.getName(),
                product.getDescription());
    }
}
