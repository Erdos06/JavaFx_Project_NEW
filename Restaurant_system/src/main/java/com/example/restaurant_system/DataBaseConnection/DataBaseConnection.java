package com.example.restaurant_system.DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Connecting to our DataBase
public class DataBaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_system";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}