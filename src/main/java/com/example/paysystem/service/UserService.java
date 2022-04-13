package com.example.paysystem.service;


import com.example.paysystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User findUserByUsername(String username);
}
