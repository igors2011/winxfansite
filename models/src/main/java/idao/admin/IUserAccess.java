package idao.admin;

import models.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IUserAccess {
    List<User> getAllUsers();
    List<User> getUsersByRole(String role);
    User getUserById(int id);
    void updateUser(User user);
    void deleteUser(User user);
    void createUser(User user);
    List<String> getUserEmails();
}
