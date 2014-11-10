package com.models;

// What is a Search Request?
public class SearchRequest {
    private boolean isOneWay;
    private String origin;
    private String destination;
    private int passengers;
    private String date;
    private String returnDate;
    private int solutions;

    // Why are we overwriting the toString method?
    @Override
    public String toString() {
        return "SearchRequest{" +
                "isOneWay=" + isOneWay +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", passengers=" + passengers +
                ", date='" + date + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", solutions=" + solutions +
                '}';
    }

    public boolean getIsOneWay() {
        return isOneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.isOneWay = oneWay;
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

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSolutions() {
        return solutions;
    }

    public void setSolutions(int solutions) {
        this.solutions = solutions;
    }
}
