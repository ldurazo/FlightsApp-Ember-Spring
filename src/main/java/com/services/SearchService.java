package com.services;

import com.connectors.FlightConnector;
import com.connectors.QpxConnector;
import com.factories.ConnectorFactory;
import com.models.SearchRequestQpx;
import com.models.TripOption;
import com.utils.GlobalObjectMapper;
import com.qpxutils.JsonRequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class SearchService {

    private GlobalObjectMapper objectMapper ;
    private JsonRequestParser jsonRequestParser;
    private ConnectorFactory connectorFactory;

    @Autowired
    public SearchService(GlobalObjectMapper objectMapper, JsonRequestParser jsonRequestParser, ConnectorFactory connectorFactory) {
        this.objectMapper = objectMapper;
        this.jsonRequestParser = jsonRequestParser;
        this.connectorFactory = connectorFactory;
    }

    public String getJsonStringForRequest(SearchRequestQpx searchRequestQpx){
        return jsonRequestParser.createJsonStringSearchRequest(searchRequestQpx);
    }

    public String getFlightsAsJsonString(String jsonStringForRequest, String connectorName){
        FlightConnector connector = connectorFactory.createConnector(connectorName);
        List list = connector.getFlights(jsonStringForRequest);
        try {
            return objectMapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
            return "No flights found";
        }
    }
}
