package com.winxfansite.admin.dao;

import idao.admin.IBackupAccess;
import idao.admin.ILogAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class BackupAccess implements IBackupAccess {
    @Autowired
    private ILogAccess logAccess;
    public String getArticles() {
        try {
            StringBuilder result = new StringBuilder();
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM articles;";
            ResultSet resultSet = statement.executeQuery(query);
            logAccess.logInfo("Получен список статей для бэкапа");
            while (resultSet.next()) {
                String id = "" + resultSet.getInt("id");
                String header = resultSet.getString("header");
                String shortDescr = resultSet.getString("shortdescr");
                String longDescr = resultSet.getString("longDescr");
                String type = resultSet.getString("type");
                String author = resultSet.getString("author");
                String visits = "" + resultSet.getInt("visits");
                result.append("id=").append(id).append(" header=").append(header).append(" shortDescr=").append(shortDescr).append(" longDescr=").append(longDescr).append(" type=").append(type).append(" author=").append(author).append(" visits=").append(visits).append('\n');
            }
            connection.close();
            return String.valueOf(result);
        } catch (SQLException | IOException e) {
            logAccess.logError("Ошибка при получении списка статей для бэкапа");
            throw new RuntimeException(e);
        }
    }
    public String getUsers() {
        try {
            StringBuilder result = new StringBuilder();
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users;";
            ResultSet resultSet = statement.executeQuery(query);
            logAccess.logInfo("Получен список пользователей для бэкапа");
            while (resultSet.next()) {
                String id = "" + resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String enabled = "" + resultSet.getBoolean("enabled");
                result.append("id=").append(id).append(" username=").append(username).append(" password=").append(password).append(" role=").append(role).append(" enabled=").append(enabled).append('\n');
            }
            connection.close();
            return String.valueOf(result);
        } catch (SQLException | IOException e) {
            logAccess.logError("Ошибка при получении списка пользователей для бэкапа");
            throw new RuntimeException(e);
        }
    }
    public String getComments() {
        try {
            StringBuilder result = new StringBuilder();
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM comments;";
            ResultSet resultSet = statement.executeQuery(query);
            logAccess.logInfo("Получен список комментариев для бэкапа");
            while (resultSet.next()) {
                String id = "" + resultSet.getInt("id");
                String message = resultSet.getString("message");
                String userId = "" + resultSet.getInt("userid");
                String articleId = resultSet.getString("articleid");
                result.append("id=").append(id).append(" message=").append(message).append(" userid=").append(userId).append(" articleid=").append(articleId).append('\n');
            }
            connection.close();
            return String.valueOf(result);
        } catch (SQLException | IOException e) {
            logAccess.logInfo("Ошибка при получении списка комментариев для бэкапа");
            throw new RuntimeException(e);
        }
    }
    public String getLogs() {
        try {
            StringBuilder result = new StringBuilder();
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM logs;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id = "" + resultSet.getInt("id");
                String date = "" + resultSet.getTimestamp("date");
                String type = resultSet.getString("type");
                String message = resultSet.getString("message");
                result.append("id=").append(id).append(" date=").append(date).append(" type=").append(type).append(" message=").append(message).append('\n');
            }
            logAccess.logInfo("Получен список логов для бэкапа");
            connection.close();
            return String.valueOf(result);
        } catch (SQLException | IOException e) {
            logAccess.logError("Ошибка при получении списка логов для бэкапа");
            throw new RuntimeException(e);
        }
    }
}
