package backend.project.services;

import backend.project.entities.User;

import java.util.List;

public interface UserService {

    User insertUser(User user);
    User insertUser(String username, String password, Boolean active, Long authorityId);
    void deleteUser(Long id);
    void deleteUser(Long id, boolean forced);
    List<User> listAllUsers();
    User findById(Long id);
    List<User> findByActiveStatus(Boolean active);
}
