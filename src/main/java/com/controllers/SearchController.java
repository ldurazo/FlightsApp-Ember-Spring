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
    public @ResponseBody ResponseEntity<String> search(@RequestBody SearchRequest searchRequest) {
        //TODO this should not be hardcoded, find a way to make it a feature
        searchRequest.setSolutions(10);
        //Logic to parse the parameters as json
        SearchRequestQpx search = new SearchRequestQpx(searchRequest);

        String jsonStringForRequest = searchService.getJsonStringForRequest(search);
        String jsonResponse = searchService.getFlightsAsJsonString(jsonStringForRequest);

        return new ResponseEntity<String>(jsonResponse, HttpStatus.OK);
    }
}