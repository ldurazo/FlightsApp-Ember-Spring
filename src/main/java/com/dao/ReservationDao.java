package com.dao;

import com.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public boolean save(Object record) {
        Connection connection = null;
        Reservation reservation = (Reservation) record;
        String query = "INSERT INTO RESERVATIONS (name, last_name, cost, email)" +
                "VALUES(?, ?, ?, ?)";
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(Reservation.NAME_COLUMN, reservation.getName());
            preparedStatement.setString(Reservation.LAST_NAME_COLUMN, reservation.getLast_name());
            preparedStatement.setString(Reservation.COST_COLUMN, reservation.getCost());
            preparedStatement.setString(Reservation.EMAIL_COLUMN, reservation.getEmail());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object getRecord(int id) {
        String query = "SELECT name, last_name, cost, email FROM RESERVATIONS WHERE ID=" + id;
        return jdbcTemplate.queryForObject(query, Reservation.class, new ReservationRowMapper());
    }

    public class ReservationRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Reservation reservation = new Reservation();
            reservation.setName(resultSet.getString(Reservation.NAME_COLUMN));
            reservation.setLast_name(resultSet.getString(Reservation.LAST_NAME_COLUMN));
            reservation.setCost(resultSet.getString(Reservation.COST_COLUMN));
            reservation.setEmail(resultSet.getString(Reservation.EMAIL_COLUMN));
            return reservation;
        }
    }
}