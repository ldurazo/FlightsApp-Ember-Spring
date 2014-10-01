package com.utils;


import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class GlobalObjectMapper {
    private static ObjectMapper globalMapperInstance = new ObjectMapper();

    public static ObjectMapper getInstance() {
        globalMapperInstance.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        globalMapperInstance.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        return globalMapperInstance;
    }

    private GlobalObjectMapper() {
    }
}
