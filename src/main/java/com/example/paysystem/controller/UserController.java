package com.example.paysystem.controller;


import com.example.paysystem.dto.user.UserDtoRequest;
import com.example.paysystem.dto.user.UserDtoResponse;
import com.example.paysystem.entity.User;
import com.example.paysystem.mapper.user.UserMapper;
import com.example.paysystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserMapper userMapper;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<UserDtoResponse> userList = userService.getAllUsers()
                .stream()
                .map(userMapper::fromUserToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserDtoResponse userDtoResponse = userMapper.fromUserToDTO(userService.findUserById(id));
        return new ResponseEntity<>(userDtoResponse, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody UserDtoRequest userDtoRequest) {
      User user = userMapper.fromUserDtoForSaveToUser(userDtoRequest);
      userService.saveUser(user);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDtoRequest userDtoRequest) {
        User user = userMapper.fromUserDtoForUpdateToUser(userDtoRequest);
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
