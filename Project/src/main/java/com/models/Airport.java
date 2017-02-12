package com.models;

public class Airport {
    public static final int ID_COLUMN = 1;
    public static final int IATA_COLUMN = 2;
    public static final int LATITUDE_COLUMN = 3;
    public static final int LONGITUDE_COLUMN = 4;
    public static final int AIRPORT_NAME_COLUMN = 5;
    public static final int CITY_NAME = 6;

    private int id;
    private String iata_code;
    private String city_name;
    private String airport_name;
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", iata_code='" + iata_code + '\'' +
                ", city_name='" + city_name + '\'' +
                ", airport_name='" + airport_name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIata_code() {
        return iata_code;
    }

    public void setIata_code(String iata_code) {
        this.iata_code = iata_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
