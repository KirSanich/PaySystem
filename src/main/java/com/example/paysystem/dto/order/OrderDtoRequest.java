package com.example.paysystem.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoRequest {

    private Long consumerId;

    private Long flatId;

    private OffsetDateTime from;

    private OffsetDateTime to;

}
