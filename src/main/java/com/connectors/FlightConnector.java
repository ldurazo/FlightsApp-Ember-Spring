package com.connectors;

import com.models.SearchRequest;

import java.util.List;

/**
 * ldurazo on 11/10/14.
 */
public interface FlightConnector<T> {
    List<T> getFlights(SearchRequest searchRequest);
}
