package com.example.paysystem.service;

import com.example.paysystem.entity.User;
import com.example.paysystem.exception.user.UserWithCurrentIdNotFound;
import com.example.paysystem.exception.user.UserWithUsernameNotFound;
import com.example.paysystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Deleting user with id:{}", id);
        userRepository.delete(findUserById(id));
    }

    @Override
    public User findUserByUsername(String username) {
        log.info("Searching user with username:{}", username);
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserWithUsernameNotFound("No found user with username:" + username));
    }

    @Override
    public User findUserById(Long id) {
        log.info("Searching user with id:{}", id);
        return userRepository.findById(id).orElseThrow(() -> new UserWithCurrentIdNotFound("No found user with id:" + id));
    }

    @Override
    public void createUser(User user) {
        log.info("Saving user");
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        log.info("Searching user with id:{} for updating", user.getId());
        User userFromDb = findUserById(user.getId());
        log.info("Updating user");
        User updatedUser = User.builder()
                .id(userFromDb.getId())
                .username(user.getUsername())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .build();
        userRepository.save(updatedUser);
        return updatedUser;
    }

}
