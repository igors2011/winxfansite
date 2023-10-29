package com.winxfansite.fansite.daopostgres;

import com.winxfansite.fansite.models.Article;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchAccess {
    public List<Article> find(String query) {
        List<Article> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM articles WHERE header LIKE '%" + query + "%'";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            while (resultSet.next()) {
                Article newArticle = new Article(resultSet.getInt("id"), resultSet.getString("header"), resultSet.getString("shortdescr"), resultSet.getString("longdescr"), resultSet.getString("articletype"), resultSet.getString("author"));
                result.add(newArticle);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}