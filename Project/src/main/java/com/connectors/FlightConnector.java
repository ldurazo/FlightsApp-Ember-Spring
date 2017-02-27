package com.connectors;

import com.models.SearchRequest;

import java.util.List;

public interface FlightConnector<T> {
    public static final String QPX="qpx";
    List<T> getFlights(SearchRequest searchRequest);
}
