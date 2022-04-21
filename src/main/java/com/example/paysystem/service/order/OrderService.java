package com.example.paysystem.service.order;

import com.example.paysystem.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    List<Order> getAllOrders();

    void createOrder(Order order);

    void deleteOrderById(Long id);

    void deleteOrderByTrackNumber(Long trackNumber);

    Order findOrderById(Long id);

    Order findOrderByTrackNumber(Long trackNumber);
}
