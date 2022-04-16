package com.example.paysystem.service;


import com.example.paysystem.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void deleteUserById(Long id);

    User findUserByUsername(String name);

    User findUserById(Long id);

    void saveUser(User user);

    User updateUser(User user);

}
