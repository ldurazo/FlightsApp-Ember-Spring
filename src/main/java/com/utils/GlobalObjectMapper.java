package com.utils;


import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.stereotype.Component;

@Component
public class GlobalObjectMapper extends ObjectMapper{

    public GlobalObjectMapper() {
        this.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    }

}
