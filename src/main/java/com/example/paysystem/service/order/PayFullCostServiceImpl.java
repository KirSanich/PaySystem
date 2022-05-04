package com.example.paysystem.service.order;


import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.entity.Operation;
import com.example.paysystem.entity.Order;
import com.example.paysystem.entity.Transfer;
import com.example.paysystem.service.email.EmailService;
import com.example.paysystem.service.transfer.BalanceManager;
import com.example.paysystem.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PayFullCostServiceImpl implements PayFullCostService {

    @Autowired
    private final BalanceManager balanceManager;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final UserService userService;

    @Override
    public void payFullCostAfterExpireDate(Order order) {

        if (order.getOrderStatus().getAbbreviation().equalsIgnoreCase("regist")) {
            AccountDetails accountDetails = order.getAccountDetails();
            Transfer transfer = Transfer.builder()
                    .dateTransfer(OffsetDateTime.now())
                    .operation(Operation.BalanceDown)
                    .transferMoney(order.getTotalPrice().subtract(order.getPrepay()))
                    .accountDetails(accountDetails)
                    .build();
            balanceManager.updateAccountBalance(transfer);
            log.info("debiting the remaining part of the amount from account in the amount of:" + order.getTotalPrice().subtract(order.getPrepay()));
            emailService.sendMessage(userService.findUserById(order.getUser().getId()).getEmail(),
                    "You have accepted the order:" + order.getFlat().getId() + "\n"
                            + "Total price:" + order.getTotalPrice() + "$ " + "\n"
                            + "From date:" + order.getFromUtc() + "\n"
                            + "To date:" + order.getToUtc() + "\n"
                            + "--------------------------" + "\n"
                            + "You dont deny the order, therefore, we have deducted the remaining amount to pay for the order from your account" + "\n"
                            + "With Pleasure, BookingPaySystem.com" + "\n"
                    ,
                    "Accept Order with number:" + order.getTrackNumber());

            AccountDetails accountDetails1 = order.getAccountDetails();
            Transfer transfer1 = Transfer.builder()
                    .dateTransfer(OffsetDateTime.now())
                    .operation(Operation.BalanceUp)
                    .transferMoney(order.getTotalPrice().subtract(order.getPrepay()))
                    .accountDetails(accountDetails1)
                    .build();
            balanceManager.updateAccountBalance(transfer1);
            log.info("Adding money on owner account:"+accountDetails1);
            emailService.sendMessage(userService.findUserById(order.getUser().getId()).getEmail(),
                    "Your flat have accepted the with order:" + order.getFlat().getId() + "\n"
                            + "Total price:" + order.getTotalPrice() + "$ " + "\n"
                            + "From date:" + order.getFromUtc() + "\n"
                            + "To date:" + order.getToUtc() + "\n"
                            + "--------------------------" + "\n"
                            + "With Pleasure, BookingPaySystem.com" + "\n"
                    ,
                    "Consumer accept order with number:" + order.getTrackNumber());


            orderService.deleteOrderById(order.getId());

        }

    }

    @Scheduled(fixedRate = 60000)
    @Override
    public void callDatabase() {
        List<OffsetDateTime> expireDates = orderService.getAllOrders()
                .stream()
                .map(Order::getExpireDate)
                .collect(Collectors.toList());
        log.info("Searching order in:" + OffsetDateTime.now());
        Optional<OffsetDateTime> any = expireDates.stream().filter(expireDate -> expireDate.isBefore(OffsetDateTime.now().plusHours(3))).findAny();
        any.ifPresentOrElse(offsetDateTime -> payFullCostAfterExpireDate(orderService.findOrderByExpireDate(offsetDateTime)), RuntimeException::new);
    }
}
