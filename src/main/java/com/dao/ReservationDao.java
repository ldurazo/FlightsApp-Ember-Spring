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

    public int saveReservation(Reservation reservation) {
        String query = "INSERT INTO RESERVATIONS" +
                    "VALUES(DEFAULT, ?, ?, ?, ?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(Reservation.NAME_COLUMN, reservation.getName());
            preparedStatement.setString(Reservation.LAST_NAME_COLUMN, reservation.getLast_name());
            preparedStatement.setString(Reservation.COST_COLUMN, reservation.getCost());
            preparedStatement.setString(Reservation.EMAIL_COLUMN, reservation.getEmail());
            preparedStatement.execute();
            return reservation.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}