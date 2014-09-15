package com.models;

import java.util.Map;

/**
 * Created by ldurazo on 9/12/14.
 */
public class Request {
    public Request(Map<String, Object> request) {
        this.request = request;
    }

    public Map<String, Object> getRequest() {

        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    Map<String, Object> request;
}
