package org.teambd.sgp.dao;

import org.teambd.sgp.models.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategory implements DAO<Category> {

    private MyConnection connection;

    public DAOCategory(MyConnection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> getAll() throws SQLException {
        String sql = "SELECT id, name FROM category;";
        List<Category> categories;

        ResultSet rs = connection
                .getConnection()
                .createStatement()
                .executeQuery(sql);
        if ( rs.next() ) {
            categories = new ArrayList<>();
            do {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            } while ( rs.next() );
        } else {
            categories = null;
        }
        return categories;
    }

    @Override
    public Category getById(int id) throws SQLException {
        String sql = "SELECT id, name FROM category WHERE id = ? ;";
        Category category;

        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if ( rs.next() ) {
                category = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
        } else {
            category = null;
        }
        return category;
    }

    @Override
    public int insert(Category category) throws SQLException {
        String sql = "INSERT INTO category (name) VALUES (?);";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, category.getName());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    @Override
    public int update(Category category) throws SQLException {
        String sql = "UPDATE category SET name = ? WHERE id = ? ;";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, category.getName());
        ps.setInt(2, category.getId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM category WHERE id = ? ;";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
