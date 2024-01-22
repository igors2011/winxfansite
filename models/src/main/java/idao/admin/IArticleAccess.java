package idao.admin;

import models.Article;

import java.util.List;

public interface IArticleAccess {
    List<Article> getAllArticles();
}
