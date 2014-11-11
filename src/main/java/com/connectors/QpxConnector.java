package com.connectors;

import com.models.TripOption;
import com.qpxutils.QpxRestOperator;
import com.utils.GlobalObjectMapper;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

public class QpxConnector implements FlightConnector {
    private GlobalObjectMapper objectMapper;
    private QpxRestOperator qpxRestOperator;

    public QpxConnector(GlobalObjectMapper objectMapper, QpxRestOperator qpxRestOperator) {
        this.objectMapper = objectMapper;
        this.qpxRestOperator = qpxRestOperator;
    }

    @Override
    public List<TripOption> getFlights(String jsonStringForRequest) {
        String qpxResponse = qpxRestOperator.getFlightsFromQpxAsJsonString(jsonStringForRequest);
        try {
            JsonNode node = objectMapper.readTree(qpxResponse);
            node = node.get("trips").get("tripOption");
            return objectMapper.readValue(node, objectMapper.getTypeFactory().constructCollectionType(List.class, TripOption.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
