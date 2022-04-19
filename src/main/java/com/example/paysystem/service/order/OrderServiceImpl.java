package com.example.paysystem.service.order;

import com.example.paysystem.entity.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void createOrder(Order order) {
        log.info("Creating order");

    }
}
