package com.services;

import com.models.SearchRequestQpx;
import com.models.TripOption;
import com.utils.GlobalObjectMapper;
import com.qpxutils.QpxRestOperator;
import com.qpxutils.JsonRequestParser;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchService {

    private GlobalObjectMapper objectMapper ;
    private JsonRequestParser jsonRequestParser;
    private QpxRestOperator qpxRestOperator;

    @Autowired
    public SearchService(GlobalObjectMapper objectMapper, QpxRestOperator qpxRestOperator, JsonRequestParser jsonRequestParser) {
        this.objectMapper = objectMapper;
        this.qpxRestOperator = qpxRestOperator;
        this.jsonRequestParser = jsonRequestParser;
    }

    public String getJsonStringForRequest(SearchRequestQpx searchRequestQpx){
        return jsonRequestParser.createJsonStringSearchRequest(searchRequestQpx);
    }

    // so the service is for a Search but you return Flights in a string.
    // also a little complex to understand, I see that we are going to return only a stringified json object
    // but what does node.get("trips") and "tripOption" mean for the service?, I think this
    // logic should be a little bit more deep (even if this is as simple as just get a value)
    public String getFlightsAsJsonString(String jsonStringForRequest){
        // what this should do is something like
        // I used a connectorFactory but not needed, you could just hardcore the QpxConnector.
        //
        // ### Interesting question for you to solve:
        // What if I created an user story that would be:
        // -- As the system I want to be able to obtain flights from different Flight apis
        //  Ex: Qpx/ITASoftware, Sabre, FlightStats, Despegar.com
        //
        // :-) not needed, but would help a lot more...
        //
        // My point is, if the service is called SearchService, why does it parse and generate a flight list
        // converts it to json and then it returns it.
        //
        // Remember: S in SOLID is for Single responsibility, the service responsibility is just to call the
        // responsible objects that will do the work and return a general response for the Controller.
        //
        // FlightConnector connector = connectorFactory.createConnector("qpx");
        // List<Flight> flights = connector.getAvailableFlights();
        // ...
        // do whatever we want with the flights (as set our Airport object to detail it more, see prices, see promotions)
        // ...
        // return convertListToJson(flights);
        String qpxResponse = qpxRestOperator.getFlightsFromQpxAsJsonString(jsonStringForRequest);
        try {
            JsonNode node = objectMapper.readTree(qpxResponse);
            node = node.get("trips").get("tripOption");
            List<TripOption> list = objectMapper.readValue(node, objectMapper.getTypeFactory().constructCollectionType(List.class, TripOption.class));
            return objectMapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No flights found";
    }
}
