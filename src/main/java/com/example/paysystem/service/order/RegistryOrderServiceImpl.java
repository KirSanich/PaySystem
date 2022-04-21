package com.example.paysystem.service.order;

import com.example.paysystem.dto.order.OrderDtoRequest;
import com.example.paysystem.entity.*;
import com.example.paysystem.exception.order.OrderException;
import com.example.paysystem.exception.order.OrderWithUnavailableParameters;
import com.example.paysystem.service.accountdetails.AccountDetailsService;
import com.example.paysystem.service.email.EmailService;
import com.example.paysystem.service.flat.FlatService;
import com.example.paysystem.service.transfer.BalanceManager;
import com.example.paysystem.service.transfer.Updatable;
import com.example.paysystem.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final AccountDetailsService accountDetailsService;

    @Autowired
    private final Updatable balanceManager;

    @Autowired
    private final OrderService orderService;

    @Override
    @Transactional
    public synchronized Order registryOrder(OrderDtoRequest orderDtoRequest) {

        log.info("Trying register order");
        boolean isBooked = flatService.isFlatEnabled(orderDtoRequest.getFlatId());
        if (!isBooked) {
            Flat flat = flatService.findFlatById(orderDtoRequest.getFlatId());
            BigDecimal flatPriceForDay = flat.getPriceForDay();
            BigDecimal totalPrice = totalSumCalculatorService.calculateTotalSum(orderDtoRequest.getFrom(), orderDtoRequest.getTo(), flatPriceForDay);
            OffsetDateTime dayExpirePrepay = orderDtoRequest.getFrom().plusDays(1);
            flat.setBooked(true);
            Order order = Order.builder()
                    .totalPrice(totalPrice)
                    .user(userService.findUserById(orderDtoRequest.getConsumerId()))
                    .flat(flat)
                    .prepay(prepayPercentCalculatorService.calculatePrepayPrice(totalPrice))
                    .accountDetails(accountDetailsService.findAccountById(orderDtoRequest.getConsumerAccountId()))
                    .fromUtc(orderDtoRequest.getFrom())
                    .toUtc(orderDtoRequest.getTo())
                    .trackNumber(ThreadLocalRandom.current().nextLong())
                    .orderStatus(OrderStatus.Registered)
                    .build();
            AccountDetails accountDetails = accountDetailsService.findAccountById(orderDtoRequest.getConsumerAccountId());
            Transfer transfer = Transfer.builder()
                    .operation(Operation.BalanceDown)
                    .dateTransfer(OffsetDateTime.now())
                    .transferMoney(prepayPercentCalculatorService.calculatePrepayPrice(totalPrice))
                    .accountDetails(accountDetails)
                    .build();
            balanceManager.updateAccountBalance(transfer);
            emailService.sendMessage(userService.findUserById(orderDtoRequest.getConsumerId()).getEmail(),
                    "You have booked the flat:" + order.getFlat().getId() + "\n"
                            + "Total price:" + order.getTotalPrice() + "$ " + "\n"
                            + "Prepay:" + order.getPrepay() + "$ " + "\n"
                            + "From date:" + order.getFromUtc() + "\n"
                            + "To date:" + order.getToUtc() + "\n"
                            + "--------------------------" + "\n"
                            + "Please notify if you want cancel the order, prepay will not return if has already passed one day" + "\n"
                            + "Your expiration prepay date is:" + dayExpirePrepay,
                    "Order with number:" + order.getTrackNumber());

            return order;
        } else
            throw new OrderWithUnavailableParameters("Try to create order later or choose other parametrises");
    }

    @Override
    @Transactional
    public synchronized Order cancelOrder(Long trackNumber, String status) {

        log.info("Try to cancel order");
        Order order = orderService.findOrderByTrackNumber(trackNumber);
        if (status.equalsIgnoreCase("cancel") && !Objects.equals(order.getOrderStatus().getAbbreviation(), "cancel")) {
            order.setOrderStatus(OrderStatus.Cancelled);
            BigDecimal totalPrice = totalSumCalculatorService.calculateTotalSum(order.getFromUtc(), order.getToUtc(), order.getFlat().getPriceForDay());
            AccountDetails accountDetails = accountDetailsService.findAccountById(order.getAccountDetails().getId());
            Transfer transfer = Transfer.builder()
                    .operation(Operation.BalanceUp)
                    .dateTransfer(OffsetDateTime.now())
                    .transferMoney(prepayPercentCalculatorService.calculatePrepayPrice(totalPrice))
                    .accountDetails(accountDetails)
                    .build();
            balanceManager.updateAccountBalance(transfer);

            emailService.sendMessage(userService.findUserById(order.getUser().getId()).getEmail(),
                    "You have cancelled the order:" + order.getFlat().getId() + "\n"
                            + "Total price:" + order.getTotalPrice() + "$ " + "\n"
                            + "Prepay:" + order.getPrepay() + "$ " + "\n"
                            + "From date:" + order.getFromUtc() + "\n"
                            + "To date:" + order.getToUtc() + "\n"
                            + "--------------------------" + "\n"
                            + "Prepay will return on our account if expire day have not passed"+"\n"
                            + "With Pleasure, BookingPaySystem.com" + "\n"
                            ,
                    "Cancelling Order with number:" + order.getTrackNumber());

            return order;
        } else
            throw new OrderException("Order already cancelled or unknown status");
    }
}
