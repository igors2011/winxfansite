package com.winxfansite.usermvc.daopostgres;

import models.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
@Component
public class UserAccess {
    public static PreparedStatement prepareUserForUpdate(Connection connection, User user, String newUsername) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET (username, password, email, getmessages) = (?, ?, ?, ?) WHERE username = '" + user.getUsername() + "';");
            statement.setString(1, newUsername);
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setBoolean(4, user.isGetMessages());
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
            result.setEmail(resultSet.getString("email"));
            result.setGetMessages(resultSet.getBoolean("getmessages"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }
    private static PreparedStatement prepareUserForInsert(Connection connection, User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, role, enabled, email, getmessages) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, "ROLE_user");
            statement.setBoolean(4, true);
            statement.setString(5, user.getEmail());
            statement.setBoolean(6, user.isGetMessages());
            return statement;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    public void insertUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedUser = prepareUserForInsert(connection, user);
            preparedUser.executeUpdate();
            LogAccess.logInfo("Добавлен пользователь с именем " + user.getUsername());
            preparedUser.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            LogAccess.logError("Ошибка при добавлении пользователя с именем " + user.getUsername());
            throw new RuntimeException(e);
        }
    }
    public int getUserIdByName(String username) {
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT id FROM users WHERE username = '" + username + "';";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                connection.close();
                return resultSet.getInt("id");
            }
            return 0;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public User getUserByName(String username) {
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "';";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                connection.close();
                return resultSetToUser(resultSet);
            }
            return new User();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void editUser(User user, String newUsername) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedUser = prepareUserForUpdate(connection, user, newUsername);
            preparedUser.executeUpdate();
            LogAccess.logInfo("Изменён пользователь с именем = " + user.getUsername());
            preparedUser.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            LogAccess.logError("Ошибка при изменении пользователя с именем = " + user.getUsername());
            throw new RuntimeException(e);
        }
    }
    private static PreparedStatement prepareUserForMakingCandidate(Connection connection, User user) {
        try {
            return connection.prepareStatement("UPDATE users SET role = 'ROLE_candidate' WHERE id = " + user.getId() + ";");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void makeUserCandidate(User user) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedUser = prepareUserForMakingCandidate(connection, user);
            preparedUser.executeUpdate();
            LogAccess.logInfo("Изменён пользователь с именем = " + user.getUsername());
            preparedUser.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            LogAccess.logError("Ошибка при изменении пользователя с именем = " + user.getUsername());
            throw new RuntimeException(e);
        }
    }
}
