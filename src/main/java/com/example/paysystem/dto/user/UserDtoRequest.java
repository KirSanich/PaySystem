package com.example.paysystem.dto.user;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoRequest {

    private Long id;

    private String username;

    private String name;

    private int age;

    private String email;

}
