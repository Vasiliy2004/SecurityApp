package ru.vasa.SecurityApp.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasa.SecurityApp.models.Customer;
import ru.vasa.SecurityApp.models.Product;
import ru.vasa.SecurityApp.models.Supplier;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void update(int id, Customer customer) {

        jdbcTemplate.update("UPDATE Customers SET name=?, contact_person=?, email=? WHERE id=?",
                customer.getName(),
                customer.getContact_person(),
                customer.getEmail(),
                id);

    }

    public List<Customer> index() {
        return jdbcTemplate.query("SELECT * FROM Customers", new BeanPropertyRowMapper<>(Customer.class));
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE  FROM Customers WHERE id=?",id);
    }

    public Optional<Customer> getSupplierByName(String name) {
        return jdbcTemplate.query("SELECT * FROM Customers WHERE name=?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Customer.class)).stream().findAny();
    }


    public Customer show(int id) {

        return jdbcTemplate.query("SELECT * FROM Customers WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class))
                .stream().findAny().orElse(null);

    }


    public void save(Customer customer) {
        jdbcTemplate.update("INSERT INTO Customers (name, contact_person,email) VALUES(?,?,?)",
                customer.getName(),
                customer.getContact_person(),
                customer.getEmail());
    }
}

