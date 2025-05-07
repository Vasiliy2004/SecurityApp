package ru.vasa.SecurityApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasa.SecurityApp.models.Order;
import ru.vasa.SecurityApp.models.Product;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void update(int id, Order order) {

        jdbcTemplate.update("UPDATE Orders SET order_date=?, customer_id=?, status=? WHERE id=?",
                order.getOrder_date(),
                order.getCustomer_id(),
                order.getStatus(),
                id);

    }

    public List<Order> index() {
        return jdbcTemplate.query("SELECT * FROM Orders", new BeanPropertyRowMapper<>(Order.class));
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE  FROM Orders WHERE id=?",id);
    }



    public Order show(int id) {

        return jdbcTemplate.query("SELECT * FROM Orders WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Order.class))
                .stream().findAny().orElse(null);

    }


    public void save(Order order) {
        jdbcTemplate.update("INSERT INTO Orders (order_date, customer_id,status) VALUES(?,?,?)",
                order.getOrder_date(),
                order.getCustomer_id(),
                order.getStatus()
                );
    }
}

