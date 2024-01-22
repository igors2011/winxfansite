package com.winxfansite.usermvc.daopostgres;

import idao.user.ILogAccess;
import models.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.util.Date;

@Component
public class LogAccess implements ILogAccess {
    public static PreparedStatement prepareLog(Connection connection, Log log) {
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
        this.insertLog(new Log(new Timestamp(new Date().getTime()), "message", message));
    }
    public void logError(String message) {
        this.insertLog(new Log(new Timestamp(new Date().getTime()), "error", message));
    }
}
