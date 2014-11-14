package com.models.QpxRequest;

public class Passenger {
    private int passenger;

    public int getAdultCount() {
        return passenger;
    }

    public void setAdultCount(int passengers) {
        this.passenger = passengers;
    }

    public Passenger(int passengers) {

        this.passenger = passengers;
    }
}
