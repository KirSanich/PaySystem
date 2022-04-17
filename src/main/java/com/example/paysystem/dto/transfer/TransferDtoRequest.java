package com.example.paysystem.dto.transfer;

import com.example.paysystem.entity.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class TransferDtoRequest {

    private BigDecimal money;

    private OffsetDateTime date;

    private Long accountDetailsId;

    @JsonProperty(value = "type_operation")
    private Operation operation;
}
