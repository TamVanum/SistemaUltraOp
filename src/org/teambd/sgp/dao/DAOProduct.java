package org.teambd.sgp.dao;

import org.teambd.sgp.models.Product;

import java.sql.SQLException;
import java.util.List;

public class DAOProduct implements DAO<Product> {

    // TODO: crear funcion que recupere el producto
    // TODO: Mostrar solamente los datos marcados como activos

    @Override
    public List<Product> getAll() throws SQLException {
        return null;
    }

    @Override
    public Product getById(int id) throws SQLException {
        return null;
    }

    @Override
    public int insert(Product product) throws SQLException {
        return 0;
    }

    @Override
    public int update(Product product) throws SQLException {
        return 0;
    }

    // TODO: En vez de hacer un delete, hacer un update
    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
