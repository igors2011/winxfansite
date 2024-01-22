package idao.user;

import models.Article;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IArticleAccess {
    byte[] getImageByArticleId(int articleId);
    void insertOrUpdateArticle(Article article, byte[] image, boolean action);
    void deleteArticle(int id);
    List<Article> getAllArticles();
    List<Article> getArticlesByType(String articleType);
    Article getArticleByHeader(String header);
    void increaseVisits(Article article);
    void addComment(int userId, int articleId, String message);
    void deleteCommentById(int commentId);
}
