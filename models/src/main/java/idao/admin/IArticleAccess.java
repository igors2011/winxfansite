package idao.admin;

import models.Article;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IArticleAccess {
    List<Article> getAllArticles();
}
