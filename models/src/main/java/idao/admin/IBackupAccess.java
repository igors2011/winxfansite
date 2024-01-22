package idao.admin;

import org.springframework.stereotype.Component;

@Component
public interface IBackupAccess {
    String getArticles();
    String getUsers();
    String getComments();
    String getLogs();
}
