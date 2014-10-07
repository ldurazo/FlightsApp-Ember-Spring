package com.controllers;

import com.dao.ReservationDao;
import com.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReservationController {
public static final String RESERVATION_ENDPOINT = "/reserve";
    @Autowired
    private ReservationDao reservationDao;

    @RequestMapping(value = RESERVATION_ENDPOINT, method = RequestMethod.POST, headers = "Content-Type=application/json")
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        int saveStatus = reservationDao.saveReservation(reservation);
        return new ResponseEntity<String>(String.valueOf(saveStatus), HttpStatus.OK);
    }
}
