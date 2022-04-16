package com.example.paysystem.mapper.accountdetails;

import com.example.paysystem.dto.accountdetails.AccountDetailsDtoResponse;
import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.mapper.transfer.TransferMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AccountDetailsMapper {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final TransferMapper transferMapper;


    public AccountDetailsDtoResponse fromAccountDetailsToDTO(AccountDetails accountDetails) {
        return AccountDetailsDtoResponse.builder()
                .id(accountDetails.getId())
                .money(accountDetails.getMoney())
                .transferDTOS(accountDetails.getTransferList().stream().map(transferMapper::fromTransferToDTO).collect(Collectors.toList()))
                .userId(accountDetails.getUser().getId())
                .build();
    }
}
