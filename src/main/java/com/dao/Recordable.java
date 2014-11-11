package com.dao;

/**
 * Created by ldurazo on 10/7/14.
 */
public interface Recordable<T> {
    int save(T record);
    T getRecord(final int id, final String email);
}
