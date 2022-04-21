package com.example.paysystem.service.order;

import com.example.paysystem.entity.Order;
import com.example.paysystem.exception.order.OrderWithCurrentIdNotFound;
import com.example.paysystem.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

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

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        log.info("Deleting order with id:" + id);
        Order order = findOrderById(id);
        order.getFlat().setBooked(false);
        orderRepository.deleteOrderWithIdByNative(order.getId());
    }

    @Override
    public void deleteOrderByTrackNumber(Long trackNumber) {
        log.info("Deleting order by track number:" + trackNumber);
        Order order = findOrderByTrackNumber(trackNumber);
        order.getFlat().setBooked(false);
        orderRepository.deleteById(order.getId());

    }

    @Override
    public Order findOrderById(Long id) {
        log.info("Searching order with id:" + id);
        return orderRepository.findById(id).orElseThrow(() -> new OrderWithCurrentIdNotFound("No found order with id:" + id));
    }

    @Override
    public Order findOrderByTrackNumber(Long trackNumber) {
        log.info("Searching order with track number:" + trackNumber);
        return orderRepository.findByTrackNumber(trackNumber).orElseThrow(() -> new RuntimeException("No exist order with track number:" + trackNumber));
    }
}
