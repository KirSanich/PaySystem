package com.example.paysystem.dto.flat;

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
public class FlatDtoResponse {

    private int amountRooms;

    private Double metricArea;

    private boolean isBooked;

    private BigDecimal priceForDay;

    private OffsetDateTime fromUtc;

    private OffsetDateTime toUtc;

    private Long ownerId;
}
