package com.models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

//TODO ask bany for a better pc or more memory or solid state disk

// What is a TripOption?
public class TripOption {

    private List<Slice> slices;

    private String saleTotal;

    @JsonProperty("slice")
    public List<Slice> getSlices() {
        return slices;
    }

    public void setSlices(List<Slice> slices) {
        this.slices = slices;
    }

    @JsonProperty("saleTotal")
    public String getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(String saleTotal) {
        this.saleTotal = saleTotal;
    }

    @Override
    public String toString() {
        return "TripOption{" +
                "slices=" + slices +
                ", saleTotal='" + saleTotal + '\'' +
                '}';
    }

    // What is a Slice and why is it static?
    public static class Slice {

        @JsonProperty("segment")
        List<SliceSegment> segments;

        @Override
        // Why are we overwriting the toString method?
        public String toString() {
            return "Slice{" +
                    "segments=" + segments +
                    '}';
        }

        public static class SliceSegment {

            List<Leg> legs;

            @JsonProperty("leg")
            public List<Leg> getLegs() {
                return legs;
            }

            public void setLegs(List<Leg> legs) {
                this.legs = legs;
            }

            @Override
            public String toString() {
                return "SliceSegment{" +
                        "legs=" + legs +
                        '}';
            }

            public static class Leg {

                private String arrivalTime;
                private String departureTime;
                private String origin;
                private String destination;

                public String getDepartureTime() {
                    return departureTime;
                }

                public void setDepartureTime(String departureTime) {
                    this.departureTime = departureTime;
                }

                @JsonProperty("origin")
                public String getOrigin() {
                    return origin;
                }

                public void setOrigin(String origin) {
                    this.origin = origin;
                }

                @JsonProperty("destination")
                public String getDestination() {
                    return destination;
                }

                public void setDestination(String destination) {
                    this.destination = destination;
                }

                @JsonProperty("arrivalTime")
                public String getArrivalTime() {
                    return arrivalTime;
                }

                public void setArrivalTime(String arrivalTime) {
                    this.arrivalTime = arrivalTime;
                }

                @Override
                public String toString() {
                    return "Leg{" +
                            "arrivalTime='" + arrivalTime + '\'' +
                            ", departureTime='" + departureTime + '\'' +
                            ", origin='" + origin + '\'' +
                            ", destination='" + destination + '\'' +
                            '}';
                }


            }
        }
    }
}
