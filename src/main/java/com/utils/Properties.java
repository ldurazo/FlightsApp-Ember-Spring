package com.utils;

/**
 * TODO: ERASE AND RELOCATE ANYTHING YOU USE HERE, IS PROPERTIES A PROPER CLASS?
 */

public class Properties {
    public final static String QPX_SEARCH_URI = "https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyD43_9fVQ5ZWrBpujsLxGG5DH-BDqwF_ok";
    public final static String ERROR = "Error";
}

// Dummy reservation for Reservation POST
//        {
//        "name": "luis",
//        "last_name": "durazo",
//        "passengers": "4",
//        "flights": [
//        {
//        "departure_date": "2014/10/31",
//        "arrival_date": "2014/11/01",
//        "departure_airport": "MEX",
//        "arrival_airport": "LAX",
//        "travel_minutes": "230"
//        },
//        {
//        "departure_date": "2014/10/31",
//        "arrival_date": "2014/11/01",
//        "departure_airport": "LAX",
//        "arrival_airport": "SFO",
//        "travel_minutes": "100"
//        },
//        {
//        "departure_date": "2014/10/31",
//        "arrival_date": "2014/11/01",
//        "departure_airport": "SFO",
//        "arrival_airport": "KOR",
//        "travel_minutes": "400"
//        }
//        ],
//        "cost": "3456.45",
//        "email": "ldurazo@nearsoft.com"
//        }
// Same string but sensitive to doble quotes
// "{ \"name\": \"luis\",  \"last_name\": \"durazo\",   \"passengers\": \"4\", \"flights\": [ { \"departure_date\": \"2014/10/31\", \"arrival_date\": \"2014/11/01\",  \"departure_airport\": \"MEX\",   \"arrival_airport\": \"LAX\", \"travel_minutes\": \"230\"  }, { \"departure_date\": \"2014/10/31\",  \"arrival_date\": \"2014/11/01\",  \"departure_airport\": \"LAX\", \"arrival_airport\": \"SFO\",   \"travel_minutes\": \"100\" }, {   \"departure_date\": \"2014/10/31\",     \"arrival_date\": \"2014/11/01\",  \"departure_airport\": \"SFO\", \"arrival_airport\": \"KOR\", \"travel_minutes\": \"400\"} ],\"cost\": \"3456.45\",\"email\": \"ldurazo@nearsoft.com\"}"