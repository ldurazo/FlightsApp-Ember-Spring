package com.services;

import com.dao.ReservationDao;
import com.models.CustomResponse;
import com.models.Reservation;
import com.qpxutils.QpxRestOperator;
import com.utils.FlightsAppObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationService {
    private FlightsAppObjectMapper objectMapper;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    public ReservationService(FlightsAppObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getReservationCustomResponse(Reservation reservation) {
        CustomResponse response = new CustomResponse();
        try {
            int reservationId = saveReservation(reservation);
            response.setMessage("your reservation id is: " + reservationId);
        } catch (Exception e) {
            response.setMessage(e.getLocalizedMessage());
        }
        return objectMapper.objectToJson(response);
    }

    private int saveReservation(Reservation reservation) throws Exception {
        int reservationId;
        reservationId = reservationDao.save(reservation);
        return reservationId;
    }

    public String getReservationResponse(int id, String email) {
        Reservation reservation = reservationDao.getRecord(id, email);
        if(reservation == null){
            Map<String, Object> responseWrapper = new HashMap<String, Object>();
            responseWrapper.put("error", "Reservation not found");
            return objectMapper.objectToJson(responseWrapper);
        }
        return objectMapper.objectToJson(reservation);
    }


}
