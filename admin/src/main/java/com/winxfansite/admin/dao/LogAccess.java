package com.winxfansite.admin.dao;

import models.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Component
public class LogAccess {
    private Log resultSetToLog(ResultSet resultSet) throws SQLException {
        return new Log(resultSet.getTimestamp("date"), resultSet.getString("type"), resultSet.getString("message"));
    }
    public List<Log> getAllLogs() {
        List<Log> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM logs ORDER BY date;";
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
    public List<Log> getLogsByType(String type) {
        List<Log> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query;
            if (type.equals("all"))
            {
                query = "SELECT * FROM logs ORDER BY date;";
            }
            else
            {
                query = "SELECT * FROM logs WHERE type = '" + type + "' ORDER BY date;";
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
