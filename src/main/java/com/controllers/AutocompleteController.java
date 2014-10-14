package com.controllers;

import com.services.AutocompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutocompleteController {

    private AutocompleteService autocompleteService;

    @Autowired
    public AutocompleteController(AutocompleteService autocompleteService) {
        this.autocompleteService = autocompleteService;
    }

    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    public ResponseEntity<String> getAutocompleteInfo(@RequestParam String city){
        String suggestions = autocompleteService.getAirportSuggestions(city);
        return new ResponseEntity<String>(suggestions, HttpStatus.OK);
    }

}
