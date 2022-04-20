package com.example.paysystem.service.order;

import com.example.paysystem.entity.Order;
import com.example.paysystem.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    @Autowired
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
       log.info("Getting all orders");
       return orderRepository.findAll();
    }

    @Override
    public void createOrder(Order order) {
        log.info("Creating order");
        orderRepository.save(order);
    }
}
