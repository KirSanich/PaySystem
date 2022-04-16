package com.example.paysystem.mapper.user;

import com.example.paysystem.dto.UserDTO;
import com.example.paysystem.entity.User;
import com.example.paysystem.mapper.accountdetails.AccountDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final AccountDetailsMapper accountDetailsMapper;


    public UserDTO fromUserToDTO(User user) {
        return modelMapper.map(user,UserDTO.class);
    }

    public User fromUserDTOtoUser(UserDTO userDTO)
    {
       return User.builder()
               .username(userDTO.getUsername())
               .name(userDTO.getName())
               .age(userDTO.getAge())
               .build();
    }
}
