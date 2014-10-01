package com.models.qpxModel;


import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class SliceSegment {

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


}
