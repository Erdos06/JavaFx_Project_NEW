package com.example.restaurant_system.DataBaseConnection;

import com.example.restaurant_system.Controllers.LoginPageController;
import com.example.restaurant_system.Users.Customer;
import com.example.restaurant_system.Users.MainUser;
import com.example.restaurant_system.Users.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static String getPassword(String username) {
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);  // Теперь мы ищем конкретного пользователя
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Если пользователь найден
                    return rs.getString("password");  // Вернуть пароль
                } else {
                    System.out.println("User not found in database: " + username);
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Boolean getUsername(String username) {
        String query = "SELECT username FROM users WHERE username = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);  // Теперь мы ищем конкретного пользователя
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Если пользователь найден
                    return true; // Вернуть пароль
                } else {
                    System.out.println("User not found in database: " + username);
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isManager(String username) {
        String query = "SELECT isManager FROM users WHERE username = ?";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);)
        {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("isManager");
                }
                else {
                    return false;
                }
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveUser(Customer customer) throws SQLException {
        String query = "INSERT INTO users (username, password, name, isManager) VALUES (?, ?, ?, ?)";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setString(1, customer.getId());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getName());
            stmt.setBoolean(4, false);

            stmt.executeUpdate();
        }
    }

    public static String getName(String username) {
        String query = "SELECT name FROM users WHERE username = ?";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
                else {
                    return null;
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeUser(MainUser user) throws SQLException {
        String query = "UPDATE users SET name = ?, username = ?, password = ? WHERE username = ?";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getId());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, LoginPageController.username.get(0));
            stmt.executeUpdate();
        }
    }
}
