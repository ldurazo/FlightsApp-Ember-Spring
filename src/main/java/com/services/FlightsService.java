package com.services;

import com.connectors.FlightConnector;
import com.factories.ConnectorFactory;
import com.models.SearchRequest;
import com.utils.FlightsAppObjectMapper;
import com.utils.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class FlightsService {

    private FlightsAppObjectMapper objectMapper ;
    private ConnectorFactory connectorFactory;

    @Autowired
    public FlightsService(FlightsAppObjectMapper objectMapper, ConnectorFactory connectorFactory) {
        this.objectMapper = objectMapper;
        this.connectorFactory = connectorFactory;
    }

    public String getAvailableFlights(SearchRequest searchRequest, String connectorName){
        searchRequest.setSolutions(Properties.NUMBER_OF_RESULTTS);
        FlightConnector connector = connectorFactory.createConnector(connectorName);
        List list = connector.getFlights(searchRequest);
        try {
            return objectMapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
            return "No flights found";
        }
    }
}
