package com.example.paysystem.dto;

import com.example.paysystem.entity.Transfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class AccountDetailsDTO {

    private Long id;

    private BigDecimal money;

    private List<TransferDTO> transferDTOS;

}
