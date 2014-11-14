package com.models.TripOption;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Slice {

    @JsonProperty("segment")
    List<SliceSegment> segments;

    @Override
    public String toString() {
        return "Slice{" +
                "segments=" + segments +
                '}';
    }

}
