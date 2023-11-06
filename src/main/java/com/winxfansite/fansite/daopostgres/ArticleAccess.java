package com.winxfansite.fansite.daopostgres;

import com.winxfansite.fansite.models.Article;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleAccess {
    public static Article resultSetToArticle(ResultSet resultSet) throws SQLException {
         return new Article(resultSet.getInt("id"), resultSet.getString("header"), resultSet.getString("shortdescr"), resultSet.getString("longdescr"), resultSet.getString("type"), resultSet.getString("author"));
    }
    public List<Article> getArticlesByType(String articleType) {
        List<Article> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM articles WHERE type = '" + articleType + "';";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                Article newArticle = resultSetToArticle(resultSet);
                result.add(newArticle);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Article getArticleById(int id) {
        Article result = null;
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM articles WHERE id = '" + id + "';";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                result = resultSetToArticle(resultSet);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public Article getArticleByHeader(String header) {
        Article result = null;
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM articles WHERE header = '" + header + "';";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                result = new Article(resultSet.getInt("id"), resultSet.getString("header"), resultSet.getString("shortdescr"), resultSet.getString("longdescr"), resultSet.getString("type"), resultSet.getString("author"));
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}