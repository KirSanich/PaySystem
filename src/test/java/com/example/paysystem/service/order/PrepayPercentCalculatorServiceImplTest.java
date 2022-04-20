package com.example.paysystem.service.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


class PrepayPercentCalculatorServiceImplTest {

    private PrepayPercentCalculatorService prepayPercentCalculatorService;

    @BeforeEach
    void setUp() {
        prepayPercentCalculatorService = new PrepayPercentCalculatorServiceImpl();
    }

    @Test
    @DisplayName("Test should pass when total prepay will equals")
    void calculatePrepayPrice() {
        BigDecimal a = BigDecimal.valueOf(40.00);
        BigDecimal b = prepayPercentCalculatorService.calculatePrepayPrice(BigDecimal.valueOf(200.00));
        Assertions.assertEquals(0, a.compareTo(b));
    }
}