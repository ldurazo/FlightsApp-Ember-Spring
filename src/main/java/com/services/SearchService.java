package com.services;

import com.models.SearchRequest;
import com.models.qpxModel.TripOption;
import com.utils.GlobalObjectMapper;
import com.utils.RestClient;
import com.utils.SearchParser;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class SearchService {
    private ObjectMapper objectMapper;

    public SearchService() {
        objectMapper = GlobalObjectMapper.getInstance();
    }

    public String getJsonStringForRequest(SearchRequest search){
        String jsonStringForRequest = SearchParser.getJsonStringForSearchRequest(search);
        return jsonStringForRequest;
    }

    public String getFlightsAsJsonString(String jsonStringForRequest){
        String qpxResponse = RestClient.getFlightsFromQpxAsJsonString(jsonStringForRequest);
        try {
            JsonNode node = objectMapper.readTree(qpxResponse);
            node = node.get("trips").get("tripOption");
            List<TripOption> list = objectMapper.readValue(node, objectMapper.getTypeFactory().constructCollectionType(List.class, TripOption.class));
            System.out.println(list);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return qpxResponse;
    }


}
