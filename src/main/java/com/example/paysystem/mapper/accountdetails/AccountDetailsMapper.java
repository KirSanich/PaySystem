package com.example.paysystem.mapper.accountdetails;

import com.example.paysystem.dto.accountdetails.AccountDetailsDtoRequest;
import com.example.paysystem.dto.accountdetails.AccountDetailsDtoResponse;
import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.service.user.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class AccountDetailsMapper {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final UserService userService;

    public AccountDetailsDtoResponse fromAccountDetailsToDtoResponse(AccountDetails accountDetails) {
        return AccountDetailsDtoResponse.builder()
                .money(accountDetails.getMoney())
                .type(accountDetails.getType())
                .userId(accountDetails.getUser().getId())
                .build();
    }

    public AccountDetails fromAccountDetailsDtoForCreateToAccountDetails(AccountDetailsDtoRequest accountDetailsDtoRequest) {
        return AccountDetails.builder()
                .money(BigDecimal.ZERO)
                .type(accountDetailsDtoRequest.getType())
                .user(userService.findUserById(accountDetailsDtoRequest.getUserId()))
                .build();
    }
}
