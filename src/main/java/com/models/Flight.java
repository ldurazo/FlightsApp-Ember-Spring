package com.models;

public class Flight {
    public static final int ID_COLUMN = 1;
    public static final int DEPARTURE_DATE_COLUMN = 2;
    public static final int ARRIVAL_DATE_COLUMN = 3;
    public static final int DEPARTURE_AIRPORT_COLUMN = 4;
    public static final int ARRIVAL_AIRPORT_COLUMN = 5;
    public static final int RESERVATION_ID_COLUMN = 6;

    private int id;
    private String departure_date;
    private String arrival_date;
    private String departure_airport;
    private String arrival_airport;
    private int reservation_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }
}
