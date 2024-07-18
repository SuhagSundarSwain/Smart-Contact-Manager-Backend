package com.SCM.Services;

import com.SCM.Entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    User updateUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String id);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

}
