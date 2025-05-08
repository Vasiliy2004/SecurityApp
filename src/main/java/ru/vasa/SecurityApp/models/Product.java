package ru.vasa.SecurityApp.models;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Product {
    int id;
    String name;
    int category_id;
    double price;
    int quantity;
    int  supplier_id;


    public Product(int product_id, String name, int category_id, double price, int quantity, int supplier_id) {
        this.id = product_id;
        this.name = name;
        this.category_id = category_id;
        this.price = price;
        this.quantity = quantity;
        this.supplier_id = supplier_id;
    }

    public Product() {    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product product_id=" + id + ", name=" + name + ", category_id=" + category_id;
    }
}
