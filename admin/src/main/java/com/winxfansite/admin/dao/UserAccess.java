package com.winxfansite.admin.dao;

import idao.admin.ILogAccess;
import idao.admin.IUserAccess;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserAccess implements IUserAccess {
    @Autowired
    private ILogAccess logAccess;
    private static User safeResultSetToUser(ResultSet resultSet) {
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
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT id, username, role, enabled FROM users ORDER BY id;";
            ResultSet resultSet = statement.executeQuery(query);
            logAccess.logInfo("Получен список пользователей для администратора");
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
    public List<User> getUsersByRole(String role) {
        List<User> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT id, username, role, enabled FROM users WHERE role = '" + role + "' ORDER BY id;";
            ResultSet resultSet = statement.executeQuery(query);
            logAccess.logInfo("Получен список пользователей с ролью " + role);
            while (resultSet.next()) {
                User newUser = safeResultSetToUser(resultSet);
                result.add(newUser);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            logAccess.logError("Ошибка при получении списка пользователей с ролью " + role);
            throw new RuntimeException(e);
        }
        return result;
    }
    public User getUserById(int id) {
        try {
            User result = new User();
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT id, username, role, enabled FROM users WHERE id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(query);
            logAccess.logInfo("Получен пользователь с id = " + id);
            while (resultSet.next()) {
                result = safeResultSetToUser(resultSet);
            }
            resultSet.close();
            connection.close();
            return result;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static PreparedStatement prepareUserForUpdate(Connection connection, User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET (username, role, enabled) = (?, ?, ?) WHERE id = " + user.getId() + ";");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getRole());
            statement.setBoolean(3, user.isEnabled());
            return statement;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    public void updateUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedUser = prepareUserForUpdate(connection, user);
            preparedUser.executeUpdate();
            logAccess.logInfo("Обновлена информация о пользователе с id = " + user.getId());
            preparedUser.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            logAccess.logError("Ошибка при обновлении информации о пользователе с id = " + user.getId());
            throw new RuntimeException(e);
        }
    }
    private static PreparedStatement prepareUserForDelete(Connection connection, User user) {
        try {
            return connection.prepareStatement("DELETE FROM comments where userid = " + user.getId() + "; DELETE FROM users WHERE id = " + user.getId());
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    public void deleteUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedUser = prepareUserForDelete(connection, user);
            preparedUser.executeUpdate();
            logAccess.logInfo("Удалён пользователь с id = " + user.getId());
            preparedUser.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            logAccess.logError("Ошибка при удалении пользователя с id = " + user.getId());
            throw new RuntimeException(e);
        }
    }
    private static PreparedStatement prepareUserForCreate(Connection connection, User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, role, enabled) VALUES (?, ?, ?, ?);");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setBoolean(4, user.isEnabled());
            return statement;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    public void createUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedUser = prepareUserForCreate(connection, user);
            preparedUser.executeUpdate();
            logAccess.logInfo("Добавлен пользователь с именем " + user.getUsername());
            preparedUser.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public List<String> getUserEmails() {
        List<String> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT email FROM users WHERE email IS NOT NULL AND getmessages = true;";
            ResultSet resultSet = statement.executeQuery(query);
            logAccess.logInfo("Получен список e-mail пользователей для отправки рассылки");
            while (resultSet.next()) {
                result.add(resultSet.getString("email"));
            }
            connection.close();
        } catch (SQLException | IOException e) {
            logAccess.logError("Ошибка при получении списка e-mail пользователей для отправки рассылки");
            throw new RuntimeException(e);
        }
        return result;
    }
}
