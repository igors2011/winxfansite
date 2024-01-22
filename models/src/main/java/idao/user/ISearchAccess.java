package idao.user;

import models.Article;

import java.util.List;

public interface ISearchAccess {
    List<Article> find(String query);
}
