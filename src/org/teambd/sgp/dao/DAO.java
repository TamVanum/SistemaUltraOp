package org.teambd.sgp.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    public List<T> getAll() throws SQLException;
    public T getById(int id) throws SQLException;
    public int insert(T t) throws SQLException;
    public int update(T t) throws SQLException;
    public int delete(int id)throws SQLException;
}
