package com.services;

import com.dao.AirportDao;
import com.models.Airport;
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
        List<Airport> airportList = airportDao.getAirportName(userInput);
        if(airportList.isEmpty()){
            Map<String, Object> responseMap = new HashMap<String, Object>();
            responseMap.put("Error", "No suggestions found");
            return objectMapper.objectToJson(responseMap);
        }
        return objectMapper.objectToJson(airportList);
    }
}
