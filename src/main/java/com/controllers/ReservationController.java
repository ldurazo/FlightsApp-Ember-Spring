package com.controllers;

import com.dao.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
@SuppressWarnings("unused")
public class ReservationController {

    @Autowired
    private ReservationDao reservationDao;

    @RequestMapping(value = "/reserve", method = RequestMethod.GET)
    @ResponseBody
    public String createReservation() throws SQLException {
        //TODO change this to a POST request, make a json as input, parse it as reservation and send it to the DAO
        return reservationDao.getDataSource().getClass().toString();
    }
}
