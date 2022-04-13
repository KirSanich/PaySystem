package com.example.paysystem.dto;

import com.example.paysystem.entity.AccountDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {

    private String username;

    private String name;

    private int age;

    private List<AccountDetailsDTO> accountDetailsList;
}
