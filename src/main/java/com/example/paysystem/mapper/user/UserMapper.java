package com.example.paysystem.mapper.user;

import com.example.paysystem.dto.user.UserDtoRequest;
import com.example.paysystem.dto.user.UserDtoResponse;
import com.example.paysystem.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public UserDtoResponse fromUserToDTO(User user) {
        return UserDtoResponse.builder()
                .username(user.getUsername())
                .city(user.getCity())
                .build();
    }

    public User fromUserDtoForSaveToUser(UserDtoRequest userDtoRequest)
    {
        return User.builder()
                .username(userDtoRequest.getUsername())
                .name(userDtoRequest.getName())
                .age(userDtoRequest.getAge())
                .email(userDtoRequest.getEmail())
                .build();
    }

    public User fromUserDtoForUpdateToUser(UserDtoRequest userDtoRequest)
    {
        return User.builder()
                .id(userDtoRequest.getId())
                .username(userDtoRequest.getUsername())
                .name(userDtoRequest.getName())
                .age(userDtoRequest.getAge())
                .email(userDtoRequest.getEmail())
                .build();
    }






}
