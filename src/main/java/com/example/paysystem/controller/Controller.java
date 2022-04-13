package com.example.paysystem.controller;


import com.example.paysystem.dto.UserDTO;
import com.example.paysystem.entity.User;
import com.example.paysystem.mapper.user.UserMapper;
import com.example.paysystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class Controller {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<String> homeText(){
        return new ResponseEntity<>("Homepage access for everyone",HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(Principal principal)
    {
        System.out.println("Получил данные админ с именем:"+principal.getName());
        List<UserDTO> userList = userService.getAllUsers()
                .stream()
                .map(userMapper::fromUserToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<String> textForAuth(){
        return new ResponseEntity<>("Homepage access for authenticated user ",HttpStatus.OK);
    }
}
