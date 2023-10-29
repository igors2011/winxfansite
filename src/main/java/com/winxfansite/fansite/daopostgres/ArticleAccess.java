package com.winxfansite.fansite.daopostgres;

import com.winxfansite.fansite.models.Article;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleAccess {
    private static final String URL = "jdbc:postgresql://localhost:5432/winxfansite";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Article> getArticles(String articleType) {
        List<Article> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM articles WHERE articletype = '" + articleType + "'";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                Article newArticle = new Article(resultSet.getInt("id"), resultSet.getString("header"), resultSet.getString("shortdescr"), resultSet.getString("longdescr"), resultSet.getString("articletype"), resultSet.getString("author"));
                result.add(newArticle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public Article getArticleById(int id) {
        Article result = null;
        try {
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM articles WHERE id = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                result = new Article(resultSet.getInt("id"), resultSet.getString("header"), resultSet.getString("shortdescr"), resultSet.getString("longdescr"), resultSet.getString("articletype"), resultSet.getString("author"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<Article> findArticles(String query) {
        List<Article> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM articles WHERE header LIKE '%" + query + "%'";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                Article newArticle = new Article(resultSet.getInt("id"), resultSet.getString("header"), resultSet.getString("shortdescr"), resultSet.getString("longdescr"), resultSet.getString("articletype"), resultSet.getString("author"));
                result.add(newArticle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}