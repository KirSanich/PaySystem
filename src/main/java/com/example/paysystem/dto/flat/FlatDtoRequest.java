package com.example.paysystem.dto.flat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class FlatDtoRequest {

    private int amountRooms;

    private Double metricArea;

    private BigDecimal priceForDay;

    private Long ownerId;
}
