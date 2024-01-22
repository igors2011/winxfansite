package idao.user;

import models.Article;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ISearchAccess {
    List<Article> find(String query);
}
