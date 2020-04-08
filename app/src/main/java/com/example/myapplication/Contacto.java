package com.example.myapplication;

import java.util.Date;

public class Contacto {
    private String name;
    private String dob;
    private String email;
    private  String phone;
    private String description;

    public Contacto(String name, String email, String phone, String description, String dob) {
        this.name = name;
        this.dob= dob;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
