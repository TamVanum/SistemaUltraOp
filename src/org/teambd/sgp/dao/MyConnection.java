package org.teambd.sgp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private Connection connection;

    public MyConnection(String host, String db, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        String url = String.format("jdbc:mariadb://%s:3306/%s?user=%s&password=%s", host, db, username, password);
        connection = DriverManager.getConnection(url);
    }

    public Connection getConnection() {
        return connection;
    }
}
