package com.models.QpxRequest;

import com.models.SearchRequest;

import java.util.HashMap;
import java.util.Map;

public class QpxRequest {

    private Map<String, Object> request;
    private Passenger passengers;
    private Object[] slice = new Object[2];
    private int solutions;

    public QpxRequest(SearchRequest searchRequest) {
        this.request = new HashMap<String, Object>();
        this.slice[0] = new Slice(searchRequest.getOrigin(), searchRequest.getDestination(), searchRequest.getDate());
        if(!searchRequest.getIsOneWay()){
            this.slice[1] = new Slice(searchRequest.getDestination(), searchRequest.getOrigin(), searchRequest.getReturnDate());
        }
        this.passengers = new Passenger(searchRequest.getPassengers());
        this.solutions = searchRequest.getSolutions();

        request.put("slice", this.slice);
        request.put("passengers", this.passengers);
        request.put("solutions", this.solutions);
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }
}
