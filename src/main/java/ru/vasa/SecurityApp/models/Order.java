package ru.vasa.SecurityApp.models;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class Order {
    int id;
    String order_date;
    int customer_id;
    int count;

    public Order(int id, int count, int customer_id, String order_date) {
        this.id = id;
        this.count = count;
        this.customer_id = customer_id;
        this.order_date = order_date;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

