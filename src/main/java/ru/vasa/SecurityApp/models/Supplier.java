package ru.vasa.SecurityApp.models;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Supplier {
    int id;

    String name;
    String contact_person;
    String phone;

    public Supplier(int id, String phone, String contact_person, String name) {
        this.id = id;
        this.phone = phone;
        this.contact_person = contact_person;
        this.name = name;
    }

    public Supplier() {
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

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
