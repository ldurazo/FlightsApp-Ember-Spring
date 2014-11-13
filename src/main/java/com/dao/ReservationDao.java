package com.dao;

import com.models.Flight;
import com.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ReservationDao implements Recordable<Reservation> {
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
    public int save(Reservation record) {
        saveReservationData(record);
        saveFlightsOfReservation(record);
        return holder.getKey().intValue();
    }

    public void saveReservationData(final Reservation reservation) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //Be aware that the order of columns must be respected
                String query = "INSERT INTO RESERVATIONS (name, last_name, passengers, cost, email) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
                //Notice that the following magic number are from this particular scope to fill the query placeholders
                preparedStatement.setString(1, reservation.getName());
                preparedStatement.setString(2, reservation.getLast_name());
                preparedStatement.setInt(3, reservation.getPassengers());
                preparedStatement.setString(4, reservation.getCost());
                preparedStatement.setString(5, reservation.getEmail());
                return preparedStatement;
            }
        };
        jdbcTemplate.update(preparedStatementCreator, holder);
    }

    public void saveFlightsOfReservation(final Reservation reservation) {
        List<Flight> flightList = reservation.getFlights();
        for (final Flight flight : flightList) {
            PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    //Be aware that the order of columns must be respected
                    String query = "INSERT INTO FLIGHTS (departure_date, arrival_date, departure_airport, arrival_airport, reservation_id) VALUES(?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
                    //Notice that the following magic number are from this particular scope to fill the query placeholders
                    preparedStatement.setString(1, flight.getDepartureTime());
                    preparedStatement.setString(2, flight.getArrivalTime());
                    preparedStatement.setString(3, flight.getOrigin());
                    preparedStatement.setString(4, flight.getDestination());
                    preparedStatement.setInt(5, holder.getKey().intValue());
                    return preparedStatement;
                }
            };
            jdbcTemplate.update(preparedStatementCreator);
        }
    }

    @Override
    public Reservation getRecord(final int id, final String email) {
        try {
            String selectFromReservationsQuery = "SELECT * FROM RESERVATIONS WHERE ID=? AND EMAIL=?";
            Reservation reservation = jdbcTemplate.queryForObject(selectFromReservationsQuery,new Object[]{id,email}, new ReservationRowMapper());
            if(reservation!=null){
                String selectFromFlightsQuery = "SELECT * FROM FLIGHTS WHERE reservation_id=?";
                List<Flight> flightList = jdbcTemplate.query(selectFromFlightsQuery,new Object[]{id}, new FlightsRowMapper());
                reservation.setFlights(flightList);
            }
            return reservation;
        } catch (EmptyResultDataAccessException e){
            return new Reservation();
        }
    }

    public class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
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
            flight.setDestination(resultSet.getString(Flight.ARRIVAL_AIRPORT_COLUMN));
            flight.setOrigin(resultSet.getString(Flight.DEPARTURE_AIRPORT_COLUMN));
            flight.setArrivalTime(resultSet.getString(Flight.ARRIVAL_DATE_COLUMN));
            flight.setDepartureTime(resultSet.getString(Flight.DEPARTURE_DATE_COLUMN));
            flight.setReservation_id(resultSet.getInt(Flight.RESERVATION_ID_COLUMN));
            return flight;
        }
    }
}