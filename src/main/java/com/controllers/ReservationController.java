package com.controllers;

import com.models.Reservation;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReservationController {

    @RequestMapping(value = "/reserve", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public String createReservation(@RequestBody Reservation reservation) {

        return reservation.toString();
    }
}
