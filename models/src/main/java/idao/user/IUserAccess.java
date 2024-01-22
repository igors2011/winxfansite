package idao.user;

import models.User;
import org.springframework.stereotype.Component;

@Component
public interface IUserAccess {
    void insertUser(User user);
    int getUserIdByName(String username);
    User getUserByName(String username);
    void editUser(User user, String newUsername);
    void makeUserCandidate(User user);
}
