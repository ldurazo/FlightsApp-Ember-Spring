package com.controllers;

import com.models.Reservation;
import com.services.ReservationService;
import com.utils.GlobalObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String response = reservationService.getReservationCustomResponse(reservation);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/{id}/{email}/", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveReservation(@PathVariable("id") int id, @PathVariable("email") String email) {
        String response = reservationService.getReservationResponse(id, email);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
