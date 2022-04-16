package com.example.paysystem.dto;

import com.example.paysystem.entity.AccountDetails;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String username;

    private String name;

    private int age;

}
