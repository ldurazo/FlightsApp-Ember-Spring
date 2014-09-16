package com.models;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest {

    public SearchRequest(String origin, String destination, String date, String adultCount, String solutions) {
        this.request = new HashMap<String, Object>();
        this.slice[0] = new Slice(origin, destination, date);
        this.passengers = new Passenger(adultCount);
        this.solutions = solutions;

        request.put("slice", this.slice);
        request.put("passengers", this.passengers);
        request.put("solutions", this.solutions);
    }


    //region request
    Map<String, Object> request;

    public Map<String, Object> getRequest() {

        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }
    //endregion

    //region slice
    private Object[] slice = new Object[1];

    public class Slice {
        public static final String SLICE = "slice";
        private String origin;
        private String destination;
        private String date;

        public Slice(String origin, String destination, String date) {
            this.origin = origin;
            this.destination = destination;
            this.date = date;
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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
    //endregion

    //region passenger;
    private Passenger passengers;

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
    //endregion

    private String solutions;
}
