package com.winxfansite.usermvc.daopostgres;

import models.Article;
import models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;

public class UserAccess {
    public static PreparedStatement prepareUser(Connection connection, User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (login, password, role, enabled) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getLogin());
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
            result.setLogin(resultSet.getString("login"));
            result.setPassword(resultSet.getString("password"));
            result.setRole(resultSet.getString("role"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static User getUser(String login, String password) {
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
    public static User getUserByName(String login) {
        try {
            User result = null;
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM users WHERE login = " + login + ";";
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
    public static void setUser(User user) {
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
}
