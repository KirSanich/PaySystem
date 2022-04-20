package com.example.paysystem.service.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.OffsetDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class TotalSumCalculatorServiceImpl implements TotalSumCalculatorService {


    private final static int discount = 10;

    @Override
    public BigDecimal calculateTotalSum(OffsetDateTime from, OffsetDateTime to, BigDecimal priceForDay) {

        log.info("Calculating total sum");
        long daysForBooking = Duration.between(from, to).toDays();
        if (daysForBooking > 30) {
            return (priceForDay.multiply(BigDecimal.valueOf(daysForBooking))).multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100L), RoundingMode.HALF_EVEN);
        } else
            return priceForDay.multiply(BigDecimal.valueOf(daysForBooking));
    }

}
