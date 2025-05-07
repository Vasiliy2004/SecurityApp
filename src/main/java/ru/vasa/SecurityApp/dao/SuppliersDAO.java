package ru.vasa.SecurityApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasa.SecurityApp.models.Product;
import ru.vasa.SecurityApp.models.Supplier;

import java.util.List;
import java.util.Optional;

@Component
public class SuppliersDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SuppliersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void update(int id, Supplier supplier) {

        jdbcTemplate.update("UPDATE Suppliers SET name=?, contact_person=?, phone=? WHERE id=?",
                supplier.getName(),
                supplier.getContact_person(),
                supplier.getPhone(),
                id);

    }

    public List<Supplier> index() {
        return jdbcTemplate.query("SELECT * FROM Suppliers", new BeanPropertyRowMapper<>(Supplier.class));
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE  FROM Suppliers WHERE id=?",id);
    }

    public Optional<Supplier> getSupplierByName(String name) {
        return jdbcTemplate.query("SELECT * FROM Suppliers WHERE name=?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Supplier.class)).stream().findAny();
    }


    public Supplier show(int id) {

        return jdbcTemplate.query("SELECT * FROM Suppliers WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Supplier.class))
                .stream().findAny().orElse(null);

    }


    public void save(Supplier supplier) {
        jdbcTemplate.update("INSERT INTO Suppliers (name, contact_person,phone) VALUES(?,?,?)",
                supplier.getName(),
                supplier.getContact_person(),
                supplier.getPhone());
    }
}
