package com.controllers;

import com.models.SearchRequest;
import com.models.SearchRequestQpx;
import com.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value="/search", produces="application/json", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public @ResponseBody ResponseEntity<String> getAvailableFlights(@RequestBody SearchRequest searchRequest) {
        //TODO this should not be hardcoded, find a way to make it a feature
        searchRequest.setSolutions(10);
        //The following search object receives the request from the frontend and converts it to something
        //that is actually legible by GoogleQPX service
        SearchRequestQpx search = new SearchRequestQpx(searchRequest);
        //the request is the json created to send to QPX
        String request = searchService.getJsonStringForRequest(search);
        //the response makes a call to qpx and retrieves their response.
        //TODO hardcoded connector name is just not ok fella'
        String response = searchService.getFlightsAsJsonString(request, "qpx");

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}