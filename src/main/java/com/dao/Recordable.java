package com.dao;

/**
 * Created by ldurazo on 10/7/14.
 */
public interface Recordable<T> {
    public int save(T record);
    public T getRecord(int id);
}
