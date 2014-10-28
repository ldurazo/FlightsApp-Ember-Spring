package com.models;

public class Flight {
    public static final int ID_COLUMN = 1;
    public static final int DEPARTURE_DATE_COLUMN = 2;
    public static final int ARRIVAL_DATE_COLUMN = 3;
    public static final int DEPARTURE_AIRPORT_COLUMN = 4;
    public static final int ARRIVAL_AIRPORT_COLUMN = 5;
    public static final int RESERVATION_ID_COLUMN = 6;

    private int id;
    private String departureTime;
    private String arrivalTime;
    private String origin;
    private String destination;
    private int reservation_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", reservation_id=" + reservation_id +
                '}';
    }
}
