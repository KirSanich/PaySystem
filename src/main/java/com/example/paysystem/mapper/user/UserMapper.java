package com.example.paysystem.mapper.user;

import com.example.paysystem.dto.UserDTO;
import com.example.paysystem.entity.User;
import com.example.paysystem.mapper.accountdetails.AccountDetailsMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    private final AccountDetailsMapper accountDetailsMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper, AccountDetailsMapper accountDetailsMapper) {
        this.modelMapper = modelMapper;
        this.accountDetailsMapper = accountDetailsMapper;
    }


    public UserDTO fromUserToDTO(User user)
    {
        return UserDTO.builder()
                .username(user.getUsername())
                .name(user.getName())
                .age(user.getAge())
                .accountDetailsList(user.getAccountDetails().stream().map(accountDetailsMapper::fromAccountDetailsToDTO).collect(Collectors.toList()))
                .build();

    }
}
