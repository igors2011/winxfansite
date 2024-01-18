package com.winxfansite.admin.dao;

import models.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserAccess {
    public static PreparedStatement prepareUser(Connection connection, User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, role, enabled) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setBoolean(4, user.isEnabled());
            return statement;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static User resultSetToUser(ResultSet resultSet) {
        User result = new User();
        try {
            result.setId(resultSet.getInt("id"));
            result.setUsername(resultSet.getString("username"));
            result.setPassword(resultSet.getString("password"));
            result.setRole(resultSet.getString("role"));
            result.setEnabled(resultSet.getBoolean("enabled"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static User safeResultSetToUser(ResultSet resultSet) {
        User result = new User();
        try {
            result.setId(resultSet.getInt("id"));
            result.setUsername(resultSet.getString("username"));
            result.setRole(resultSet.getString("role"));
            result.setEnabled(resultSet.getBoolean("enabled"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }
    public User getUser(String login, String password) {
        try {
            User result = null;
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM users WHERE login = " + login + " AND password = " + password + ";";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                result = resultSetToUser(resultSet);
            }
            connection.close();
            return result;
        }
        catch (SQLException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void setUser(User user) {
        try {
            var connection = DBConnection.getConnection();
            PreparedStatement preparedUser = prepareUser(connection, user);
            preparedUser.executeUpdate();
            preparedUser.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT id, username, role, enabled FROM users ORDER BY id;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User newUser = safeResultSetToUser(resultSet);
                result.add(newUser);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
