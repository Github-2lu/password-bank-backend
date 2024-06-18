package com.tulu.userms.user;

import com.tulu.userms.user.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    String addUser(User user);
    User findUserById(Long id);
    boolean deleteUserById(Long id);
    boolean updateUserById(Long id, User updatedUser);
}
