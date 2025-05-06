package ru.vasa.SecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasa.SecurityApp.dao.ProductDAO;
import ru.vasa.SecurityApp.models.Product;

@Component
public class ProductValidator implements Validator {
    private final ProductDAO productDAO;
    private final JdbcTemplate jdbcTemplate;

    @Autowired//Можно без него
    public ProductValidator(ProductDAO productDAO, JdbcTemplate jdbcTemplate) {
        this.productDAO = productDAO;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;


    }
}
