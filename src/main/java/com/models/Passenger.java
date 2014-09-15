package com.models;

/**
 * Created by ldurazo on 9/15/14.
 */
public class Passenger {
    private String adultCount;

    public String getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(String adultCount) {
        this.adultCount = adultCount;
    }

    public Passenger(String adultCount) {

        this.adultCount = adultCount;
    }
}
