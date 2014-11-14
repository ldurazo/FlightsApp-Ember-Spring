package com.factories;

import com.connectors.FlightConnector;
import com.connectors.QpxConnector;
import com.utils.FlightsAppObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectorFactory{
    private FlightsAppObjectMapper objectMapper;

    @Autowired
    public ConnectorFactory(FlightsAppObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public FlightConnector createConnector(String connectorName) {
        if(connectorName.equals("qpx")){
            return new QpxConnector(objectMapper);
        }
        return null;
    }
}
