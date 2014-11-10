package com.controllers;

import com.models.SearchRequest;
import com.models.SearchRequestQpx;
import com.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// this controller is weird (does not tell me anything about what it does)
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value="/search", produces="application/json", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public @ResponseBody ResponseEntity<String> search(@RequestBody SearchRequest searchRequest) {

        // first, we are going to do a search?, the user sent us a SearchRequest, so the front-end does generate
        // the search request, now we just return a search?
        // I think you should rename this method to getAvailableFlights or I dont know, getAirAvailability.

        //TODO this should not be hardcoded, find a way to make it a feature // yah, what is a solution?
        searchRequest.setSolutions(10);
        //Logic to parse the parameters as json
        SearchRequestQpx search = new SearchRequestQpx(searchRequest);

        // var name should be "request"
        String jsonStringForRequest = searchService.getJsonStringForRequest(search);
        // var name should be "response"
        String jsonResponse = searchService.getFlightsAsJsonString(jsonStringForRequest);

        return new ResponseEntity<String>(jsonResponse, HttpStatus.OK);    }
}