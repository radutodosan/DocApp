package com.example.docapp;

public class Appointment {

    private String docname, specialization, date;

    public Appointment(String name, String specialization, String location) {
        this.docname = name;
        this.specialization = specialization;
        this.date = location;
    }

    public String getDocName() {
        return docname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getDate() {
        return date;
    }
}
