package com.example.docapp;

public class Doc {

    private String name, specialization, location;

    public Doc(String name, String specialization, String location) {
        this.name = name;
        this.specialization = specialization;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getLocation() {
        return location;
    }
}
