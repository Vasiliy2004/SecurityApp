package ru.vasa.SecurityApp.models;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class Order {
    int id;
    @NotEmpty(message = "Name shoud not Empty")
    @Size(min=2,max=30,message = "Massage shod be 2-30")
    String name;
    Date order_date;
    int customer_id;
    String status;

    public Order(int id, String status, int customer_id, Date order_date, String name) {
        this.id = id;
        this.status = status;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.name = name;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

