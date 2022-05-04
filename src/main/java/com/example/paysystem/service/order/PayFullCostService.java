package com.example.paysystem.service.order;

import com.example.paysystem.entity.Order;

public interface PayFullCostService {

    void payFullCostAfterExpireDate(Order order);

    void callDatabase();
}
