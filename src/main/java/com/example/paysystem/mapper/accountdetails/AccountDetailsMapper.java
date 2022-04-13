package com.example.paysystem.mapper.accountdetails;

import com.example.paysystem.dto.AccountDetailsDTO;
import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.mapper.transfer.TransferMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDetailsMapper {

    private final ModelMapper modelMapper;

    private final TransferMapper transferMapper;

    @Autowired
    public AccountDetailsMapper(ModelMapper modelMapper, TransferMapper transferMapper) {
        this.modelMapper = modelMapper;
        this.transferMapper = transferMapper;
    }

    public AccountDetailsDTO fromAccountDetailsToDTO(AccountDetails accountDetails)
    {
        return AccountDetailsDTO.builder()
                .id(accountDetails.getId())
                .money(accountDetails.getMoney())
                .transferDTOS(accountDetails.getTransferList().stream().map(transferMapper::fromTransferToDTO).collect(Collectors.toList()))
                .build();
    }
}
