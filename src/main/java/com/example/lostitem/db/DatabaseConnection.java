package com.example.lostitem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;
    private Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DatabaseConnection() throws SQLException {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        this.connection = DriverManager.getConnection(bundle.getString("db.url"),
                bundle.getString("db.username"),
                bundle.getString("db.password"));
    }

    public static DatabaseConnection getDBConnection() throws SQLException{
        if (databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void commit() throws SQLException {
        this.connection.commit();
    }
}