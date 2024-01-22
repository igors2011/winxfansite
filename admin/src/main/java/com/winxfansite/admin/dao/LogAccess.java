package com.winxfansite.admin.dao;

import idao.admin.ILogAccess;
import models.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
}
