package com.example.paysystem.service.order;

import com.example.paysystem.dto.order.OrderDtoRequest;
import com.example.paysystem.entity.Flat;
import com.example.paysystem.entity.Order;
import com.example.paysystem.exception.order.OrderWithUnavailableParameters;
import com.example.paysystem.service.flat.FlatService;
import com.example.paysystem.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RegistryOrderServiceImpl implements RegistryOrderService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final FlatService flatService;

    @Autowired
    private final PrepayPercentCalculatorService prepayPercentCalculatorService;

    @Autowired
    private final TotalSumCalculatorService totalSumCalculatorService;

    @Override
    public synchronized Order registryOrder(OrderDtoRequest orderDtoRequest) {

        log.info("Trying register order");

        boolean isBooked = flatService.isFlatEnabled(orderDtoRequest.getFlatId());

        if (!isBooked) {
            Flat flat = flatService.findFlatById(orderDtoRequest.getFlatId());
            BigDecimal flatPriceForDay = flat.getPriceForDay();
            BigDecimal totalPrice = totalSumCalculatorService.calculateTotalSum(orderDtoRequest.getFrom(), orderDtoRequest.getTo(), flatPriceForDay);
            flat.setBooked(true);
            return Order.builder()
                    .totalPrice(totalPrice)
                    .user(userService.findUserById(orderDtoRequest.getConsumerId()))
                    .flat(flat)
                    .prepay(prepayPercentCalculatorService.calculatePrepayPrice(totalPrice))
                    .fromUtc(orderDtoRequest.getFrom())
                    .toUtc(orderDtoRequest.getTo())
                    .build();



        } else
            throw new OrderWithUnavailableParameters("Try to create order later or choose other parametrises");
    }
}
