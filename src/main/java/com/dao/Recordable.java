package com.dao;

/**
 * Created by ldurazo on 10/7/14.
 */
public interface Recordable {
    public boolean save(Object record);
    public Object getRecord(int id);
}