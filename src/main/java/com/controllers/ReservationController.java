package com.controllers;

import com.models.CustomResponse;
import com.models.Reservation;
import com.services.ReservationService;
import com.utils.GlobalObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        int statusSaved = reservationService.saveReservation(reservation);
        String response;
        if (statusSaved > 0) {
            response = objectMapper.objectToJson(new CustomResponse("Reservation saved successfully with id: " + statusSaved + " please keep this number safe"));
        } else {
            response = objectMapper.objectToJson(new CustomResponse("Reservation not saved")); // why did the reservation fail?, is the database?, problem parsing?, how would you know in the future if there was an error? :-)
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    //TODO add email and name as path variables
    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveReservation(@PathVariable int id) {
        String errorResponse;
        String json = reservationService.getReservationResponse(id);
        return new ResponseEntity<String>(json, HttpStatus.OK);
    }

}
