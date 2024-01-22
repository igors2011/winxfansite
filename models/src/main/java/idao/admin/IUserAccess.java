package idao.admin;

import models.User;

import java.util.List;

public interface IUserAccess {
    User getUser(String login, String password);
    void setUser(User user);
    List<User> getAllUsers();
    List<User> getUsersByRole(String role);
    User getUserById(int id);
    void updateUser(User user);
    void deleteUser(User user);
    void createUser(User user);
    List<String> getUserEmails();
}
