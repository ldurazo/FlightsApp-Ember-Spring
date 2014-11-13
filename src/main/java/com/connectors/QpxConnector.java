package com.connectors;

import com.models.SearchRequest;
import com.models.SearchRequestQpx;
import com.models.TripOption;
import com.qpxutils.QpxRestOperator;
import com.utils.FlightsAppObjectMapper;
import org.codehaus.jackson.JsonNode;

import java.io.IOException;
import java.util.List;

public class QpxConnector implements FlightConnector {
    private FlightsAppObjectMapper objectMapper;

    public QpxConnector(FlightsAppObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<TripOption> getFlights(SearchRequest searchRequest) {
        try {
            String jsonStringForRequest = objectMapper.writeValueAsString(new SearchRequestQpx(searchRequest));
            String qpxResponse = QpxRestOperator.getFlightsFromQpx(jsonStringForRequest);
            JsonNode node = objectMapper.readTree(qpxResponse);
            node = node.get("trips").get("tripOption");
            return objectMapper.readValue(node, objectMapper.getTypeFactory().constructCollectionType(List.class, TripOption.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
