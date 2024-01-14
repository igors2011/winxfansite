package com.winxfansite.usermvc.daopostgres;

import models.Article;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleAccess {
    public static Article resultSetToArticle(ResultSet resultSet) throws SQLException {
        Article result = new Article();
        result.setId(resultSet.getInt("id"));
        result.setHeader(resultSet.getString("header"));
        result.setShortDescr(resultSet.getString("shortdescr"));
        result.setLongDescr(resultSet.getString("longdescr"));
        result.setType(resultSet.getString("type"));
        result.setAuthor(resultSet.getString("author"));
        result.setURL("/articles/" + result.getHeader());
        switch (result.getType()) {
            case "fairies":
                result.setViewType("Феи");
                break;
            case "schools":
                result.setViewType("Школы");
                break;
        }
        return result;
    }
    public static PreparedStatement prepareArticleForInsert(Connection connection, Article article) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO articles (header, shortdescr, longdescr, type, author) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, article.getHeader());
            statement.setString(2, article.getShortDescr());
            statement.setString(3, article.getLongDescr());
            statement.setString(4, article.getType());
            statement.setString(5, article.getAuthor());
            return statement;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    public static PreparedStatement prepareArticleForUpdate(Connection connection, Article article) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE articles SET (header, shortdescr, longdescr, type, author) = (?, ?, ?, ?, ?) WHERE id = " + article.getId() + ";");
            statement.setString(1, article.getHeader());
            statement.setString(2, article.getShortDescr());
            statement.setString(3, article.getLongDescr());
            statement.setString(4, article.getType());
            statement.setString(5, article.getAuthor());
            return statement;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    public static PreparedStatement prepareArticleForDelete(Connection connection, int id) {
        try {
            return connection.prepareStatement("DELETE FROM articles WHERE id = " + id + ";");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void insertOrUpdateArticle(Article article, boolean action) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedArticle;
            if (!action) {
                preparedArticle = prepareArticleForInsert(connection, article);
            }
            else {
                preparedArticle = prepareArticleForUpdate(connection, article);
            }
            preparedArticle.executeUpdate();
            preparedArticle.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void deleteArticle(int id) {
        try {
            var connection = DBConnection.getConnection();
            PreparedStatement preparedArticle = prepareArticleForDelete(connection, id);
            preparedArticle.executeUpdate();
            preparedArticle.close();
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Article> getAllArticles() {
        List<Article> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM articles ORDER BY type;";
            ResultSet resultSet = statement.executeQuery(query);
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
    public List<Article> getArticlesByType(String articleType) {
        List<Article> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM articles WHERE type = '" + articleType + "';";
            ResultSet resultSet = statement.executeQuery(query);
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
            String query = "SELECT * FROM articles WHERE id = '" + id + "';";
            ResultSet resultSet = statement.executeQuery(query);
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
            String query = "SELECT * FROM articles WHERE header = '" + header + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result = resultSetToArticle(resultSet);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}