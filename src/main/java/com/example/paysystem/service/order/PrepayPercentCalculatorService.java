package com.example.paysystem.service.order;

import java.math.BigDecimal;

public interface PrepayPercentCalculatorService {

    BigDecimal calculatePrepayPrice(BigDecimal totalPrice);
}

