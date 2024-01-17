package com.winxfansite.usermvc.daopostgres;


import models.Article;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchAccess {
    public List<Article> find(String query) {
        List<Article> result = new ArrayList<>();
        try {
            var connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String SQLQuery = "SELECT * FROM articles WHERE UPPER(header) LIKE UPPER('%" + query + "%');";
            ResultSet resultSet = statement.executeQuery(SQLQuery);
            LogAccess.logInfo("Выполнен поиск статей по запросу " + query);
            while (resultSet.next()) {
                Article newArticle = ArticleAccess.resultSetToArticle(resultSet);
                result.add(newArticle);
            }
            connection.close();
        } catch (SQLException | IOException e) {
            LogAccess.logError("Ошибка поиска статей по запросу" + query);
            throw new RuntimeException(e);
        }
        return result;
    }
}
