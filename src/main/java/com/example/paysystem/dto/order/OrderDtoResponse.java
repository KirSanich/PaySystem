package com.example.paysystem.dto.order;

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
public class OrderDtoResponse {

    private Long consumerId;

    private BigDecimal totalPrice;

    private Long flatId;

    private BigDecimal prepay;

    private OffsetDateTime from;

    private OffsetDateTime to;

}
