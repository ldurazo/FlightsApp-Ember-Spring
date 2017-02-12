package com.connectors;

import com.models.SearchRequest;

import java.util.List;

public interface FlightConnector<T> {
    public static final String QPX="qpx";
    //public static final String FLIGHT_STATS = "FS";
    List<T> getFlights(SearchRequest searchRequest);
}
