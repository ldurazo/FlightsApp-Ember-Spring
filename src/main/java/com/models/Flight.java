package com.models;

public class Flight {

    private long id;

    private String departure_date;
    private String arrival_date;
    private String departure_airport;
    private String arrival_airport;
    private int travel_minutes;

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departure_date=" + departure_date +
                ", arrival_date=" + arrival_date +
                ", departure_airport='" + departure_airport + '\'' +
                ", arrival_airport='" + arrival_airport + '\'' +
                ", travel_minutes=" + travel_minutes +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public int getTravel_minutes() {
        return travel_minutes;
    }

    public void setTravel_minutes(int travel_minutes) {
        this.travel_minutes = travel_minutes;
    }

}
