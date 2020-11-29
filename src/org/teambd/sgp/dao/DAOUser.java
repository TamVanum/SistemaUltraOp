package org.teambd.sgp.dao;

import org.teambd.sgp.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUser implements DAO<User> {

    private MyConnection connection;

    public DAOUser(MyConnection connection) {
        this.connection = connection;
    }

    public User login(String username, String password) throws SQLException {
        String sql = "SELECT id, username, password, permission, is_active FROM user " +
                "WHERE username = ? AND password = SHA2(?, 512);";
        User user;

        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if ( rs.next() ) {
            user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("permission"),
                    rs.getBoolean("is_active")
            );
        } else {
            user = null;
        }
        return user;
    }

    public int rejoin(int id) throws SQLException {
        String sql = "UPDATE user SET is_active = 1,  WHERE id = ? ;";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT id, username, password, permission, is_active FROM user;";
        List<User> users;

        ResultSet rs = connection
                .getConnection()
                .createStatement()
                .executeQuery(sql);
        if ( rs.next() ) {
            users = new ArrayList<>();
            do {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("permission"),
                        rs.getBoolean("is_active")
                ));
            } while ( rs.next() );
        } else {
            users = null;
        }
        return users;
    }

    @Override
    public User getById(int id) throws SQLException {
        String sql = "SELECT id, username, password, permission, is_active FROM user WHERE id = ? ;";
        User user;

        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if ( rs.next() ) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("permission"),
                        rs.getBoolean("is_active")
                );
        } else {
            user = null;
        }
        return user;
    }

    @Override
    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO user (username, password, permission) VALUES (?, SHA2(?, 512), ?);";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getPermission());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    @Override
    public int update(User user) throws SQLException {
        String sql = "UPDATE user SET username = ?, password = ? WHERE id = ? ;";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setInt(3, user.getId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "UPDATE user SET is_active = 0,  WHERE id = ? ;";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
