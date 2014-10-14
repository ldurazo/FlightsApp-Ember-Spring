package com.qpxutils;

import com.models.SearchRequestQpx;
import com.utils.GlobalObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class JsonRequestParser {

    private  GlobalObjectMapper objectMapper;

    @Autowired
    public JsonRequestParser(GlobalObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String createJsonStringSearchRequest(SearchRequestQpx searchRequestQpx) {
        String jsonStringForRequest = null;
        try {
            jsonStringForRequest = objectMapper.writeValueAsString(searchRequestQpx);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStringForRequest;
    }

}
