package com.dao;

import com.models.Flight;
import com.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class ReservationDao implements Recordable {
    //TODO ask or investigate if this holder should be injected somehow
    private KeyHolder holder = new GeneratedKeyHolder();

    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public boolean save(Object record) {
        Reservation reservation = (Reservation) record;
        saveReservationData(reservation);
        saveFlightsOfReservation(reservation);
        return true;
    }

    public void saveReservationData(final Reservation reservation) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //Be aware that the order of columns must be respected
                String query = "INSERT INTO RESERVATIONS (name, last_name, passengers, cost, email) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
                //Notice that the following values have a magic -1, because the id serial column is missing in the query
                preparedStatement.setString(Reservation.NAME_COLUMN - 1, reservation.getName());
                preparedStatement.setString(Reservation.LAST_NAME_COLUMN - 1, reservation.getLast_name());
                preparedStatement.setInt(Reservation.PASSENGERS_COLUMN - 1, reservation.getPassengers());
                preparedStatement.setString(Reservation.COST_COLUMN - 1, reservation.getCost());
                preparedStatement.setString(Reservation.EMAIL_COLUMN - 1, reservation.getEmail());
                return preparedStatement;
            }
        };
        jdbcTemplate.update(preparedStatementCreator, holder);
    }

    public void saveFlightsOfReservation(final Reservation reservation){
        List<Flight> flightList = reservation.getFlights();
        for(final Flight flight : flightList){
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //Be aware that the order of columns must be respected
                String query = "INSERT INTO FLIGHTS (departure_date, arrival_date, departure_airport, arrival_airport, reservation_id) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
                //Notice that the following values have a magic -1, because the id serial column is missing in the query
                preparedStatement.setString(Flight.DEPARTURE_DATE_COLUMN- 1, flight.getDeparture_date());
                preparedStatement.setString(Flight.ARRIVAL_DATE_COLUMN - 1, flight.getArrival_date());
                preparedStatement.setString(Flight.DEPARTURE_AIRPORT_COLUMN - 1, flight.getDeparture_airport());
                preparedStatement.setString(Flight.ARRIVAL_AIRPORT_COLUMN - 1, flight.getArrival_airport());
                preparedStatement.setInt(Flight.RESERVATION_ID_COLUMN - 1, holder.getKey().intValue());
                return preparedStatement;
            }
        };
        jdbcTemplate.update(preparedStatementCreator);
        }
    }

    @Override
    public Object getRecord(int id) {
        String selectFromReservationsQuery = "SELECT * FROM RESERVATIONS WHERE ID=" + id;
        Reservation reservation = jdbcTemplate.queryForObject(selectFromReservationsQuery, new ReservationRowMapper());
        String selectFromFlightsQuery = "SELECT * FROM FLIGHTS WHERE reservation_id=" + id;
        List<Flight> flightList = jdbcTemplate.query(selectFromFlightsQuery, new FlightsRowMapper());
        reservation.setFlights(flightList);
        return reservation;
    }

    public class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
            Reservation reservation = new Reservation();
            reservation.setId(resultSet.getInt(Reservation.ID_COLUMN));
            reservation.setName(resultSet.getString(Reservation.NAME_COLUMN));
            reservation.setLast_name(resultSet.getString(Reservation.LAST_NAME_COLUMN));
            reservation.setPassengers(resultSet.getInt(Reservation.PASSENGERS_COLUMN));
            reservation.setCost(resultSet.getString(Reservation.COST_COLUMN));
            reservation.setEmail(resultSet.getString(Reservation.EMAIL_COLUMN));
            return reservation;
        }
    }

    public class FlightsRowMapper implements RowMapper<Flight> {
        @Override
        public Flight mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Flight flight = new Flight();
            flight.setId(resultSet.getInt(Flight.ID_COLUMN));
            flight.setArrival_airport(resultSet.getString(Flight.ARRIVAL_AIRPORT_COLUMN));
            flight.setDeparture_airport(resultSet.getString(Flight.DEPARTURE_AIRPORT_COLUMN));
            flight.setArrival_date(resultSet.getString(Flight.ARRIVAL_DATE_COLUMN));
            flight.setDeparture_date(resultSet.getString(Flight.DEPARTURE_DATE_COLUMN));
            flight.setReservation_id(resultSet.getInt(Flight.RESERVATION_ID_COLUMN));
            return flight;
        }
    }
}