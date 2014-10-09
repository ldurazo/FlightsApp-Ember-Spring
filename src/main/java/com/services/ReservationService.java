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

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    public ReservationService(GlobalObjectMapper objectMapper, QpxRestOperator qpxRestOperator) {
        this.objectMapper = objectMapper;
        this.qpxRestOperator = qpxRestOperator;
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
