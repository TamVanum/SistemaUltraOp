package org.teambd.sgp.dao;

import org.teambd.sgp.models.PriceHistory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPriceHistory implements DAO<PriceHistory> {

    private MyConnection connection;

    public DAOPriceHistory(MyConnection connection) {
        this.connection = connection;
    }

    @Override
    public List<PriceHistory> getAll() throws SQLException {
        String sql = "SELECT id, product_id_fk, actual_price, new_price, update_date FROM price_history;";
        List<PriceHistory> priceHistories;

        ResultSet rs = connection
                .getConnection()
                .createStatement()
                .executeQuery(sql);
        if ( rs.next() ) {
            priceHistories = new ArrayList<>();
            do {
                priceHistories.add(new PriceHistory(
                        rs.getInt("id"),
                        rs.getInt("product_id_fk"),
                        rs.getInt("actual_price"),
                        rs.getInt("new_price"),
                        rs.getDate("update_date")
                ));
            } while ( rs.next() );
        } else {
            priceHistories = null;
        }
        return priceHistories;
    }

    @Override
    public PriceHistory getById(int id) throws SQLException {
        String sql = "SELECT id, product_id_fk, actual_price, new_price, update_date FROM price_history " +
                "WHERE id = ? ;";
        PriceHistory priceHistory;

        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if ( rs.next() ) {
                priceHistory = new PriceHistory(
                        rs.getInt("id"),
                        rs.getInt("product_id_fk"),
                        rs.getInt("actual_price"),
                        rs.getInt("new_price"),
                        rs.getDate("update_date")
                );
        } else {
            priceHistory = null;
        }
        return priceHistory;
    }

    @Override
    public int insert(PriceHistory priceHistory) throws SQLException {
        return 0;
    }

    @Override
    public int update(PriceHistory priceHistory) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
