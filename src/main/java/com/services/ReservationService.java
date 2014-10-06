package com.services;

import com.qpxutils.QpxRestOperator;
import com.utils.GlobalObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationService {
    private GlobalObjectMapper objectMapper ;

    private QpxRestOperator qpxRestOperator;

    @Autowired
    public ReservationService(GlobalObjectMapper objectMapper, QpxRestOperator qpxRestOperator) {
        this.objectMapper = objectMapper;
        this.qpxRestOperator = qpxRestOperator;
    }

}
