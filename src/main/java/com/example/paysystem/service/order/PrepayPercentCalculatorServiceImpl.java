package com.example.paysystem.service.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
@AllArgsConstructor
public class PrepayPercentCalculatorServiceImpl implements PrepayPercentCalculatorService {

    private static final int currentPercent = 20;

    @Override
    public BigDecimal calculatePrepayPrice(BigDecimal totalPrice) {
        log.info("Calculate prepay on base total price information");
        return totalPrice.multiply(BigDecimal.valueOf(currentPercent)).divide(BigDecimal.valueOf(100L), RoundingMode.HALF_EVEN);
    }
}
