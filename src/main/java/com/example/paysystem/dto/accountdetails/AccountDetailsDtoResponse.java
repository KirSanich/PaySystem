package com.example.paysystem.dto.accountdetails;

import com.example.paysystem.dto.transfer.TransferDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetailsDtoResponse {

    private BigDecimal money;

    private String type;

    private Long userId;

}
