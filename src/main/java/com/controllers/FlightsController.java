package com.controllers;

import com.connectors.FlightConnector;
import com.models.SearchRequest;
import com.services.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlightsController {
    private FlightsService flightsService;

    @Autowired
    public FlightsController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @RequestMapping(value="/search", produces="application/json", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public @ResponseBody ResponseEntity<String> getAvailableFlights(@RequestBody SearchRequest searchRequest) {
        String response = flightsService.getAvailableFlights(searchRequest, FlightConnector.QPX);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}