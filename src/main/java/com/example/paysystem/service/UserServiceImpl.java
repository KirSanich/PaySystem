package com.example.paysystem.service;

import com.example.paysystem.entity.User;
import com.example.paysystem.exception.UserWithUsernameNotFound;
import com.example.paysystem.repository.RoleRepository;
import com.example.paysystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(()->new UserWithUsernameNotFound("No found user with username:"+username));
    }
}
