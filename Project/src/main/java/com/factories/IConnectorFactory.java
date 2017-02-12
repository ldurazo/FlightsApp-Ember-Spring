package com.factories;

import com.connectors.FlightConnector;

public interface IConnectorFactory {
    FlightConnector createConnector(String connectorName);
}
