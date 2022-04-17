package com.example.paysystem.service.user;


import com.example.paysystem.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void deleteUserById(Long id);

    User findUserByUsername(String name);

    User findUserById(Long id);

    void createUser(User user);

    User updateUser(User user);

}
