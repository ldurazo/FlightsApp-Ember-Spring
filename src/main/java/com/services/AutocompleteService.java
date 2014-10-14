package com.services;

import com.dao.AirportDao;
import com.models.Airport;
import com.utils.GlobalObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutocompleteService {
    private GlobalObjectMapper objectMapper;

    @Autowired
    private AirportDao airportDao;

    @Autowired
    public AutocompleteService(GlobalObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getAirportSuggestions(String userInput){
        List<Airport> airportList = airportDao.getAirportName(userInput);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        if(airportList.isEmpty()){
            responseMap.put("Airports", "No suggestions found");
            return objectMapper.objectToJson(responseMap);
        }
        responseMap.put("Airports", airportList);
        return objectMapper.objectToJson(responseMap);
    }
}
