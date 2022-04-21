package com.example.paysystem.mapper.order;

import com.example.paysystem.dto.order.OrderDtoResponse;
import com.example.paysystem.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderMapper {

    public OrderDtoResponse fromOrderToOrderDtoResponse(Order order) {

        return OrderDtoResponse.builder()
                .consumerId(order.getUser().getId())
                .flatId(order.getFlat().getId())
                .totalPrice(order.getTotalPrice())
                .prepay(order.getPrepay())
                .from(order.getFromUtc())
                .to(order.getToUtc())
                .trackNumber(order.getTrackNumber())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
