package com.example.paysystem.controller;


import com.example.paysystem.dto.UserDTO;
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
        List<UserDTO> userList = userService.getAllUsers()
                .stream()
                .map(userMapper::fromUserToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserDTO userDTO = userMapper.fromUserToDTO(userService.findUserById(id));
        return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
      User user = userMapper.fromUserDTOtoUser(userDTO);
      userService.saveUser(user);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.fromUserDTOtoUser(userDTO);
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
