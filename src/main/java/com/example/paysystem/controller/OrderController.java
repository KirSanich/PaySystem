package com.example.paysystem.controller;

import com.example.paysystem.dto.order.OrderDtoRequest;
import com.example.paysystem.dto.order.OrderDtoResponse;
import com.example.paysystem.entity.Order;
import com.example.paysystem.mapper.flat.FlatMapper;
import com.example.paysystem.mapper.order.OrderMapper;
import com.example.paysystem.repository.OrderRepository;
import com.example.paysystem.security.UserService;
import com.example.paysystem.service.order.OrderService;
import com.example.paysystem.service.order.PrepayPercentCalculatorService;
import com.example.paysystem.service.order.RegistryOrderService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final RegistryOrderService registryOrderService;

    @Autowired
    private final OrderMapper orderMapper;

    @Autowired
    private final OrderRepository orderRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllOrders() {

        List<OrderDtoResponse> orderList = orderService.getAllOrders()
                .stream()
                .map(orderMapper::fromOrderToOrderDtoResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderList, HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderDtoRequest orderDtoRequest, Authentication authentication) {

        userService.verifyId(authentication, orderDtoRequest.getConsumerId());
        Order order = registryOrderService.registryOrder(orderDtoRequest);
        orderService.createOrder(order);
        OrderDtoResponse orderDtoResponse = orderMapper.fromOrderToOrderDtoResponse(order);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderWithId(@PathVariable Long id, Authentication authentication) {
        Order order = orderService.findOrderById(id);
        userService.verifyId(authentication, order.getUser().getId());
        OrderDtoResponse orderDtoResponse = orderMapper.fromOrderToOrderDtoResponse(order);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.FOUND);
    }

    @GetMapping("/{trackNumber}/track")
    public ResponseEntity<?> getOrderWithTrackNumber(@PathVariable Long trackNumber, Authentication authentication) {
        Order order = orderService.findOrderByTrackNumber(trackNumber);
        userService.verifyId(authentication, order.getUser().getId());
        OrderDtoResponse orderDtoResponse = orderMapper.fromOrderToOrderDtoResponse(order);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{trackNumber}")
    public ResponseEntity<?> deleteOrderByTrackNumber(@PathVariable Long trackNumber, Authentication authentication) {
        Order order = orderService.findOrderByTrackNumber(trackNumber);
        userService.verifyId(authentication, orderService.findOrderByTrackNumber(trackNumber).getUser().getId());
        orderService.deleteOrderByTrackNumber(order.getTrackNumber());
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id, Authentication authentication) {
        Order order = orderService.findOrderById(id);
        userService.verifyId(authentication, orderService.findOrderById(id).getUser().getId());
        orderService.deleteOrderById(order.getId());
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @DeleteMapping("/cancel/{trackNumber}")
    public ResponseEntity<?> cancelOrderByTrackNumber(@PathVariable Long trackNumber, @RequestBody OrderDtoRequest orderDtoRequest, Authentication authentication) {
        Order order = orderService.findOrderByTrackNumber(trackNumber);
        userService.verifyId(authentication, order.getUser().getId());
        Order orderForCancel = registryOrderService.cancelOrder(trackNumber, orderDtoRequest.getOrderStatus().getAbbreviation());
        orderService.deleteOrderById(orderForCancel.getId());
        return new ResponseEntity<>(HttpStatus.GONE);
    }


}

