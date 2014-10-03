package com.qpxutils;

import com.models.SearchRequestModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;

public class JsonRequestParser {

    public static String createJsonStringSearchRequest(SearchRequestModel searchRequestModel){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,true);
        String jsonStringForRequest = null;
        try {
            jsonStringForRequest = objectMapper.writeValueAsString(searchRequestModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStringForRequest;
    }

}
