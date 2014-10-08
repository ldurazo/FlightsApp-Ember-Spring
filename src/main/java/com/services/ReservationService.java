package com.services;

import com.dao.ReservationDao;
import com.models.Reservation;
import com.qpxutils.QpxRestOperator;
import com.utils.GlobalObjectMapper;
import org.codehaus.jackson.JsonGenerationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ReservationService {
    private GlobalObjectMapper objectMapper;
    private QpxRestOperator qpxRestOperator;
    private ReservationDao reservationDao;

    @Autowired
    public ReservationService(GlobalObjectMapper objectMapper, QpxRestOperator qpxRestOperator, ReservationDao reservationDao) {
        this.objectMapper = objectMapper;
        this.qpxRestOperator = qpxRestOperator;
        this.reservationDao = reservationDao;
    }

    public boolean saveReservation(Reservation reservation){
        return reservationDao.save(reservation);
    }

    public String getReservationAsJson(int id) throws IOException {
        Reservation reservation = (Reservation) reservationDao.getRecord(id);
        String json = objectMapper.writeValueAsString(reservation);
        return json;
    }


}
