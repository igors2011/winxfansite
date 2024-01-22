package com.winxfansite.admin.dao;

import idao.admin.ILogAccess;
import models.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class LogAccess implements ILogAccess {
    private Log resultSetToLog(ResultSet resultSet) throws SQLException {
        return new Log(resultSet.getTimestamp("date"), resultSet.getString("type"), resultSet.getString("message"));
    }
    public List<Log> getAllLogs() {
        List<Log> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM logs ORDER BY date DESC;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Log newLog = resultSetToLog(resultSet);
                result.add(newLog);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<Log> getLogsByType(String type, Timestamp dateFrom, Timestamp dateTo) {
        List<Log> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query;
            if (type.equals("all"))
            {
                query = "SELECT * FROM logs WHERE date BETWEEN '" + dateFrom + "' AND '" + dateTo + "' ORDER BY date DESC;";
            }
            else
            {
                query = "SELECT * FROM logs WHERE type = '" + type + "' AND date BETWEEN '" + dateFrom + "' AND '" + dateTo + "' ORDER BY date DESC;";
            }
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Log newLog = resultSetToLog(resultSet);
                result.add(newLog);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    private static PreparedStatement prepareLog(Connection connection, Log log) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (date, type, message) VALUES (?, ?, ?)");
            statement.setTimestamp(1, log.getDate());
            statement.setString(2, log.getType());
            statement.setString(3, log.getMessage());
            return statement;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    private void insertLog(Log log) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedLog = prepareLog(connection, log);
            preparedLog.executeUpdate();
            preparedLog.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void logInfo(String message) {
        this.insertLog(new Log(new Timestamp(new java.util.Date().getTime()), "message", message));
    }
    public void logError(String message) {
        this.insertLog(new Log(new Timestamp(new Date().getTime()), "error", message));
    }
}
