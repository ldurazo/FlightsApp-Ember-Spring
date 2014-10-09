package com.dao;

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

@Component
public class ReservationDao implements Recordable {

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
    public boolean save(final Object record) {
            KeyHolder holder = new GeneratedKeyHolder();
            PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    Reservation reservation = (Reservation) record;
                    //Be aware that the order of columns must be respected
                    String query = "INSERT INTO RESERVATIONS (name, last_name, passengers, cost, email) VALUES(?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
                    //Notice that the following values have a magic -1, because the id column is missing in the query
                    preparedStatement.setString(Reservation.NAME_COLUMN-1, reservation.getName());
                    preparedStatement.setString(Reservation.LAST_NAME_COLUMN-1, reservation.getLast_name());
                    preparedStatement.setInt(Reservation.PASSENGERS_COLUMN - 1, reservation.getPassengers());
                    preparedStatement.setString(Reservation.COST_COLUMN-1, reservation.getCost());
                    preparedStatement.setString(Reservation.EMAIL_COLUMN-1, reservation.getEmail());
                    return preparedStatement;
                }
            };
            jdbcTemplate.update(preparedStatementCreator, holder);
            //TODO insert this mofo to the flights db holder.getKey()
            System.out.println(((Reservation) record).getFlights().size());
            return true;
    }

    @Override
    public Object getRecord(int id) {
        String query = "SELECT * FROM RESERVATIONS WHERE ID=" + id;
        return jdbcTemplate.queryForObject(query, new ReservationRowMapper());
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
}