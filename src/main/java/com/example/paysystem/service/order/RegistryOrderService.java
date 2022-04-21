package com.example.paysystem.service.order;

import com.example.paysystem.dto.order.OrderDtoRequest;
import com.example.paysystem.entity.Order;

public interface RegistryOrderService {

    Order registryOrder(OrderDtoRequest orderDtoRequest);

    Order cancelOrder(Long trackNumber,String status);

}

