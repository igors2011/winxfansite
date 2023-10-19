package com.winxfansite.fansite.dataaccesspostgres;

import com.winxfansite.fansite.models.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleAccess {
    private static ArrayList<Article> articles;
    public ArticleAccess() {
        articles = new ArrayList<>();
        articles.add(new Article(1, "Блум", "Фея огня дракона", "Длинное описание Блум", "Fairy", "Автор1"));
        articles.add(new Article(2,"Стелла", "Фея Солнца и Луны", "Длинное описание Стеллы", "Fairy", "Автор2"));
        articles.add(new Article(3,"Алфея","Лучшая школа для фей","Длинное описание Алфеи", "School", "Автор1"));
        articles.add(new Article(4,"Облачная башня","Школа ведьм","Длинное описание Облачной башни", "School", "Автор1"));
    }


    public List<Article> getArticles(String articleType) {
        return articles.stream().filter(article -> article.getArticleType().equals(articleType)).toList();
    }
    public Article getArticleById(int id) {
        return articles.stream().filter(article -> article.getId() == id).findFirst().orElse(null);
    }
}