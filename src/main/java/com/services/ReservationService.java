package com.services;

import com.dao.ReservationDao;
import com.models.Reservation;
import com.qpxutils.QpxRestOperator;
import com.utils.GlobalObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationService {
    private GlobalObjectMapper objectMapper;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    public ReservationService(GlobalObjectMapper objectMapper, QpxRestOperator qpxRestOperator) {
        this.objectMapper = objectMapper;
    }

    public int saveReservation(Reservation reservation){
        return reservationDao.save(reservation);
    }

    public String getReservationResponse(int id) {
        Reservation reservation = (Reservation) reservationDao.getRecord(id);
        if(reservation == null){
            Map<String, Object> responseWrapper = new HashMap<String, Object>();
            responseWrapper.put("error", "Reservation not found");
            return objectMapper.objectToJson(responseWrapper);
        }
        return objectMapper.objectToJson(reservation);
    }


}
