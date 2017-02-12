package com.services;

import com.dao.AirportDao;
import com.models.Airport;
import com.utils.FlightsAppConstants;
import com.utils.FlightsAppObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutocompleteService {
    private FlightsAppObjectMapper objectMapper;

    @Autowired
    private AirportDao airportDao;

    @Autowired
    public AutocompleteService(FlightsAppObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getAirportSuggestions(String userInput){
        List<Airport> airportList = airportDao.searchAirports(userInput);
        if(airportList.isEmpty()){
            Map<String, String> responseMap = new HashMap<String, String>();
            responseMap.put(FlightsAppConstants.ERROR, "No suggestions found");
            return objectMapper.objectToJson(responseMap);
        }
        return objectMapper.objectToJson(airportList);
    }
}
