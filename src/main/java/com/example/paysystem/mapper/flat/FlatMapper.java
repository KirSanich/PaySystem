package com.example.paysystem.mapper.flat;

import com.example.paysystem.dto.flat.FlatDtoRequest;
import com.example.paysystem.dto.flat.FlatDtoResponse;
import com.example.paysystem.entity.Flat;
import com.example.paysystem.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FlatMapper {

    @Autowired
    private final UserService userService;

    public FlatDtoResponse fromFlatToDtoResponse(Flat flat) {
        return FlatDtoResponse.builder()
                .amountRooms(flat.getAmountRooms())
                .metricArea(flat.getMetricArea())
                .ownerId(flat.getUser().getId())
                .isBooked(flat.isBooked())
                .priceForDay(flat.getPriceForDay())
                .build();
    }

    public Flat fromFlatDtoRequestForCreateToFlat(FlatDtoRequest flatDtoRequest) {
        return Flat.builder()
                .amountRooms(flatDtoRequest.getAmountRooms())
                .metricArea(flatDtoRequest.getMetricArea())
                .isBooked(false)
                .user(userService.findUserById(flatDtoRequest.getOwnerId()))
                .build();
    }
}
