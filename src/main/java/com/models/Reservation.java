package com.models;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    public static final int NAME_COLUMN=1;
    public static final int LAST_NAME_COLUMN=2;
    public static final int COST_COLUMN = 3;
    public static final int EMAIL_COLUMN = 4;

    private int id;
    private String name;
    private String last_name;
    private List<Flight> flights;
    private String cost;

    public Reservation(String name, String last_name, String cost, String email, Flight...flights) {
        this();
        this.name = name;
        this.last_name = last_name;
        for (Flight f : flights){
            this.flights.add(f);
        }
        this.cost = cost;
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public Reservation(){
        this.flights = new ArrayList<Flight>();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", flights=" + flights +
                ", cost='" + cost + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
