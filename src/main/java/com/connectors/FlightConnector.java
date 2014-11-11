package com.connectors;

import java.util.List;

/**
 * ldurazo on 11/10/14.
 */
public interface FlightConnector<T> {
    List<T> getFlights(String jsonStringForRequest);
}
