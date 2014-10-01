package com.models.qpxModel;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

//TODO ask bany for a better pc or more memory or solid state disk


public class TripOption {

    List<Slice> slices;

    String saleTotal;

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
