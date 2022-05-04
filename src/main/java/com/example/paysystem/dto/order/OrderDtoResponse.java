package com.example.paysystem.dto.order;

import com.example.paysystem.entity.OrderStatus;
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
public class OrderDtoResponse {

    private Long consumerId;

    private BigDecimal totalPrice;

    private Long flatId;

    private BigDecimal prepay;

    @JsonProperty(value = "status")
    private OrderStatus orderStatus;

    private OffsetDateTime from;

    private Long trackNumber;

    private OffsetDateTime to;

    private OffsetDateTime expireDate;

}
