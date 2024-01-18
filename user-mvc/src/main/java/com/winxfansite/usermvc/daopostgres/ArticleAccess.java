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
        result.setURL("/shared/articles/" + result.getHeader());
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
    private static PreparedStatement prepareArticleForInsert(Connection connection, Article article) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO articles (header, shortdescr, longdescr, type, author, visits) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, article.getHeader());
            statement.setString(2, article.getShortDescr());
            statement.setString(3, article.getLongDescr());
            statement.setString(4, article.getType());
            statement.setString(5, article.getAuthor());
            statement.setInt(6, 0);
            return statement;
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    private static PreparedStatement prepareArticleForUpdate(Connection connection, Article article) {
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
    private static PreparedStatement prepareArticleForDelete(Connection connection, int id) {
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
            if (!action) {
                LogAccess.logInfo("Статья с названием " + article.getHeader() +  " добавлена");
            }
            else {
                LogAccess.logInfo("Статья с названием " + article.getHeader() +  " отредактирована");
            }
            preparedArticle.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            if (!action) {
                LogAccess.logError("Ошибка при добавлении статьи" + article.getHeader());
            }
            else {
                LogAccess.logError("Ошибка при редактировании статьи" + article.getHeader());
            }
            throw new RuntimeException(e);
        }
    }
    public void deleteArticle(int id) {
        try {
            var connection = DBConnection.getConnection();
            PreparedStatement preparedArticle = prepareArticleForDelete(connection, id);
            preparedArticle.executeUpdate();
            LogAccess.logInfo("Статья с id " + id + "удалена");
            preparedArticle.close();
            connection.close();
        } catch (SQLException | IOException e) {
            LogAccess.logError("Ошибка при удалении статьи с id " + id);
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
            LogAccess.logInfo("Получен полный список статей");
            while (resultSet.next()) {
                Article newArticle = resultSetToArticle(resultSet);
                result.add(newArticle);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            LogAccess.logError("Ошибка при получении полного списка статей");
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
            LogAccess.logInfo("Получен список статей категории " + articleType);
            while (resultSet.next()) {
                Article newArticle = resultSetToArticle(resultSet);
                result.add(newArticle);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            LogAccess.logError("Ошибка при получении списка статей категории " + articleType);
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
            LogAccess.logInfo("Получена статья с заголовком " + header);
            while (resultSet.next()) {
                result = resultSetToArticle(resultSet);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            LogAccess.logError("Ошибка при получении статьи с заголовком " + header);
            throw new RuntimeException(e);
        }
        return result;
    }
    private static PreparedStatement prepareIncreasingVisits(Connection connection, Article article) {
        try {
            return connection.prepareStatement("UPDATE articles SET visits = visits + 1 WHERE id = " + article.getId() + ";");
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }
    public void increaseVisits(Article article) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedArticle = prepareIncreasingVisits(connection, article);
            preparedArticle.executeUpdate();
            preparedArticle.close();
            connection.close();
        }
        catch (SQLException | IOException e)
        {
            LogAccess.logError("Ошибка при увеличении числа просмотров статьи с id = " + article.getId());
            throw new RuntimeException(e);
        }
    }
}