package ru.vasa.SecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasa.SecurityApp.dao.CategoryDAO;
import ru.vasa.SecurityApp.dao.ProductDAO;
import ru.vasa.SecurityApp.models.Category;
import ru.vasa.SecurityApp.models.Product;

@Component
public class CategoryValidator implements Validator {
    private final CategoryDAO categoryDAO;
    private final JdbcTemplate jdbcTemplate;

    @Autowired//Можно без него
    public CategoryValidator(CategoryDAO categoryDAO, JdbcTemplate jdbcTemplate) {
        this.categoryDAO = categoryDAO;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;


    }
}
