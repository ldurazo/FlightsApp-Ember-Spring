package com.factories;

import com.connectors.FlightConnector;
import com.connectors.QpxConnector;
import com.qpxutils.QpxRestOperator;
import com.utils.GlobalObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectorFactory implements IConnectorFactory{
    private GlobalObjectMapper objectMapper;
    private QpxRestOperator qpxRestOperator;

    @Autowired
    public ConnectorFactory(GlobalObjectMapper objectMapper, QpxRestOperator qpxRestOperator) {
        this.objectMapper = objectMapper;
        this.qpxRestOperator = qpxRestOperator;
    }

    @Override
    public FlightConnector createConnector(String connectorName) {
        if(connectorName.equals("qpx")){
            return new QpxConnector(objectMapper,qpxRestOperator);
        }
        return null;
    }
}
