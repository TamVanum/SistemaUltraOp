package org.teambd.sgp.dao;

import org.teambd.sgp.models.Brand;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBrand implements DAO<Brand> {

    private MyConnection connection;

    public DAOBrand(MyConnection connection) {
        this.connection = connection;
    }

    public Brand getByText(String text) throws SQLException {
        String sql = "SELECT id, name FROM brand WHERE name = ? ORDER BY id ASC;";
        Brand brand;

        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, text);
        ResultSet rs = ps.executeQuery();
        if ( rs.next() ) {
            brand = new Brand(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        } else {
            brand = null;
        }

        return brand;
    }

    @Override
    public List<Brand> getAll() throws SQLException {
        String sql = "SELECT id, name FROM brand ORDER BY id ASC;";
        List<Brand> brands;

        ResultSet rs = connection
                .getConnection()
                .createStatement()
                .executeQuery(sql);
        if ( rs.next() ) {
            brands = new ArrayList<>();
            do {
                brands.add(new Brand(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            } while (rs.next());
        } else {
            brands = null;
        }

        return brands;
    }

    @Override
    public Brand getById(int id) throws SQLException {
        String sql = "SELECT id, name FROM brand WHERE id = ? ;";
        Brand brand;

        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if ( rs.next() ) {
            brand = new Brand(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        } else {
            brand = null;
        }

        return brand;
    }

    @Override
    public int insert(Brand brand) throws SQLException {
        String sql = "INSERT INTO brand (name) VALUES (?);";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, brand.getName());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    @Override
    public int update(Brand brand) throws SQLException {
        String sql = "UPDATE brand SET name = ? WHERE id = ? ;";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, brand.getName());
        ps.setInt(2, brand.getId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public void createProcedure() throws SQLException {
        String sql = "CREATE PROCEDURE recursive_eliminator_brand(IN _id INT) " +
                "BEGIN " +
                "DECLARE have_childs INT; " +
                "SET have_childs = (SELECT COUNT(*) FROM product WHERE brand_id_fk = _id ); " +
                "IF have_childs > 0 THEN " +
                "DELETE FROM product WHERE id IN (SELECT id FROM product WHERE brand_id_fk = _id); " +
                "DELETE FROM brand WHERE id = _id; " +
                "ELSE " +
                "DELETE FROM brand WHERE id = _id; " +
                "END IF; " +
                "END";
        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.execute();
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "{ call recursive_eliminator_brand(?) }";
        CallableStatement cs = connection.getConnection().prepareCall(sql);
        cs.setInt(1, id);
        cs.execute();

        return 0;
    }
}
