package com.example.paysystem.service.order;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface TotalSumCalculatorService {

    BigDecimal calculateTotalSum(OffsetDateTime from, OffsetDateTime to, BigDecimal priceForDay);
}
