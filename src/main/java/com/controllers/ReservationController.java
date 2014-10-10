package com.controllers;

import com.models.Reservation;
import com.services.ReservationService;
import com.utils.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

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

    //TODO add email and name as path variables
    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveReservation(@PathVariable int id){
        HttpHeaders responseHeaders = new HttpHeaders();
        Date currentTime = new Date(System.currentTimeMillis());
        responseHeaders.set("Date", currentTime.toString());
        try {
            String json = reservationService.getReservationAsJson(id);
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>(Properties.ERROR, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
