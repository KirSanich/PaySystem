package com.example.paysystem.service.order;

import com.example.paysystem.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    List<Order> getAllOrders();

    void createOrder(Order order);
}
