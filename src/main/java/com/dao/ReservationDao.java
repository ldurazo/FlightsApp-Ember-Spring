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

    public static final int NAME_COLUMN=1;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public int saveReservation(Reservation reservation) {
        String query = "INSERT INTO RESERVATIONS" +
                    "VALUES(null, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(NAME_COLUMN, reservation.getName());
            preparedStatement.setString(2, reservation.getLast_name());
            preparedStatement.setString(3, reservation.getOrigin());
            preparedStatement.setString(4, reservation.getDestination());
            preparedStatement.setString(5, reservation.getCost());
            preparedStatement.setString(6, reservation.getEmail());
            preparedStatement.execute();
            return reservation.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}