package com.services;

import com.models.SearchRequest;
import com.utils.RestClient;
import com.utils.SearchParser;

public class SearchService {

    public String getJsonStringForRequest(SearchRequest search){
        String jsonStringForRequest = SearchParser.getJsonStringForSearchRequest(search);
        return jsonStringForRequest;
    }

    public String getFlightsAsJsonString(String jsonStringForRequest){
        return RestClient.getFlightsFromQpxAsJsonString(jsonStringForRequest);
    }


}
