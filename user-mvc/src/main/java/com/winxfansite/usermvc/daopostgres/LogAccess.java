package com.winxfansite.usermvc.daopostgres;

import models.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class LogAccess {
    public static PreparedStatement prepareLog(Connection connection, Log log) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (date, type, message) VALUES (?, ?, ?)");
            statement.setDate(1, log.getDate());
            statement.setString(2, log.getType());
            statement.setString(3, log.getMessage());
            return statement;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    public void insertLog(Log log) {
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
}
