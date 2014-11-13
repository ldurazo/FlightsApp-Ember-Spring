package com.utils;


import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FlightsAppObjectMapper extends ObjectMapper{

    public FlightsAppObjectMapper() {
        this.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    }

    public String objectToJson(Object object){
        try {
            return this.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
