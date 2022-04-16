package com.example.paysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDTO {

    private Long id;

    private BigDecimal money;

    private OffsetDateTime date;
}
