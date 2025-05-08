package ru.vasa.SecurityApp.models;


import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Customer{

    int id;
    String name;
    String contact_person;
    String email;

    public Customer() {
    }

    public Customer(int id, String name, String contact_person, String email) {
        this.id = id;
        this.name = name;
        this.contact_person = contact_person;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
