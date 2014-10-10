package com.controllers;

import com.models.Reservation;
import com.services.ReservationService;
import com.utils.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ReservationController {
    public static final String RESERVATION_ENDPOINT = "/reservation";
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        boolean saveStatus = reservationService.saveReservation(reservation);
        return new ResponseEntity<String>(String.valueOf(saveStatus), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET, headers = "Content-Type=application/json")
    public ResponseEntity<String> retrieveReservation(@PathVariable int id){
        String json = null;
        try {
            json = reservationService.getReservationAsJson(id);
            return new ResponseEntity<String>(json, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>(Properties.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
