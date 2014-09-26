package com.controllers;

import com.models.SearchRequest;
import com.services.SearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/*
 * TODO very important: i have to refactor everything i put here, first make it work dude, then clean your mess up.
 */


@RestController
@SuppressWarnings("unused")
public class SearchController {

    //Dummy link to perform a search with this API
    //http://localhost:8080/mvn-webapp-flights/search?origin=SFO&destination=MEX&passengers=1&date=2014-09-19
    @RequestMapping("/search")
    public @ResponseBody String search(@RequestParam(value="origin",      required=true)                      String origin,
                         @RequestParam(value="destination", required=true)                      String destination,
                         @RequestParam(value="passengers",  required=true)                      String passengers,
                         @RequestParam(value="date",        required=true)                      String date,
                         @RequestParam(value="solutions",   required=false, defaultValue = "10") String solutions) {

        //Logic to parse the parameters as json
        SearchRequest search = new SearchRequest(origin, destination, date, passengers, solutions);
        SearchService searchService = new SearchService();
        String jsonStringForRequest = searchService.getJsonStringForRequest(search);
        String response = searchService.getFlightsAsJsonString(jsonStringForRequest);




        return response;
    }
}