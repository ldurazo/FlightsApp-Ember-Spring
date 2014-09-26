package com.utils;

import com.models.SearchRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;

public class SearchParser {

    public static String getJsonStringForSearchRequest(SearchRequest searchRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,true);
        String jsonStringForRequest = null;
        try {
            jsonStringForRequest = objectMapper.writeValueAsString(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStringForRequest;
    }

}
