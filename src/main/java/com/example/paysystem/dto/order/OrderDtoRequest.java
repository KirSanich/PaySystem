package com.example.paysystem.dto.order;

import com.example.paysystem.entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoRequest {

    private Long consumerId;

    private Long consumerAccountId;

    private Long flatId;

    private OffsetDateTime from;

    @JsonProperty(value = "status")
    private OrderStatus orderStatus;

    private Long trackNumber;

    private OffsetDateTime to;

}
