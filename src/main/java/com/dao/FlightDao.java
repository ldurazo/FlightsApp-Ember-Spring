package com.dao;

import com.models.Flight;
import com.sun.prism.impl.Disposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class FlightDao implements Recordable{
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
        return false;
    }

    @Override
    public Object getRecord(int id) {
        return null;
    }
}
