package com.dao;

import com.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@SuppressWarnings("unused")
public class ReservationDao {

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

    public Boolean saveReservation(Reservation reservation) {
        String query = "INSERT INTO RESERVATIONS" +
                    "VALUES(null, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, reservation.getName());
            preparedStatement.setString(2, reservation.getLast_name());
            preparedStatement.setString(3, reservation.getOrigin());
            preparedStatement.setString(4, reservation.getDestination());
            preparedStatement.setString(5, reservation.getCost());
            preparedStatement.setString(6, reservation.getEmail());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}