package com.example.paysystem.dto.accountdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetailsDtoRequest {

    private BigDecimal money;

    private String type;

    private Long userId;

}
