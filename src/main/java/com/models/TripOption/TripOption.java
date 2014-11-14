package com.models.TripOption;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

//This is the list of flights QPX format
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


}
