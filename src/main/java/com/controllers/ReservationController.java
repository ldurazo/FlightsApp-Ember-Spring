package com.controllers;

import com.models.CustomResponse;
import com.models.Reservation;
import com.services.ReservationService;
import com.utils.GlobalObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReservationController {
    public static final String RESERVATION_ENDPOINT = "/reservation";
    private ReservationService reservationService;
    private GlobalObjectMapper objectMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, GlobalObjectMapper objectMapper) {
        this.reservationService = reservationService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        boolean statusSaved = reservationService.saveReservation(reservation);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        String response;
        if (statusSaved) {
            responseMap.put("status", new CustomResponse("Reservation saved successfully"));
            response = objectMapper.objectToJson(responseMap);
        } else {
            responseMap.put("status", new CustomResponse("Reservation not saved"));
            response = objectMapper.objectToJson(responseMap);
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    //TODO add email and name as path variables
    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveReservation(@PathVariable int id) {
        String errorResponse;
        String json = reservationService.getReservationAsJson(id);
        return new ResponseEntity<String>(json, HttpStatus.OK);
    }

}
