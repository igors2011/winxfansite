package com.winxfansite.admin.dao;

import idao.admin.IArticleAccess;
import idao.admin.ILogAccess;
import models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleAccess implements IArticleAccess {
    @Autowired
    private ILogAccess logAccess;
    private static Article resultSetToArticle(ResultSet resultSet) throws SQLException {
        Article result = new Article();
        result.setId(resultSet.getInt("id"));
        result.setHeader(resultSet.getString("header"));
        result.setVisits(resultSet.getInt("visits"));
        return result;
    }
    public List<Article> getAllArticles() {
        List<Article> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT id, header, visits FROM articles ORDER BY id;";
            ResultSet resultSet = statement.executeQuery(query);
            logAccess.logInfo("Получен полный список статей для админа");
            while (resultSet.next()) {
                Article newArticle = resultSetToArticle(resultSet);
                result.add(newArticle);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            logAccess.logError("Ошибка при получении полного списка статей для админа");
            throw new RuntimeException(e);
        }
        return result;
    }
}
