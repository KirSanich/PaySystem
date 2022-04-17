package com.example.paysystem.mapper.transfer;

import com.example.paysystem.dto.transfer.TransferDtoRequest;
import com.example.paysystem.dto.transfer.TransferDtoResponse;
import com.example.paysystem.entity.Operation;
import com.example.paysystem.entity.Transfer;
import com.example.paysystem.service.accountdetails.AccountDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@AllArgsConstructor
public class TransferMapper {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final AccountDetailsService accountDetailsService;

    public TransferDtoResponse fromTransferToDtoResponse(Transfer transfer)
    {
        return TransferDtoResponse.builder()
                .money(transfer.getTransferMoney())
                .date(transfer.getDateTransfer())
                .accountDetailsId(transfer.getAccountDetails().getId())
                .operation(transfer.getOperation())
                .build();
    }

    public Transfer fromTransferDtoRequestForCreateToTransfer(TransferDtoRequest transferDtoRequest)
    {
        return Transfer.builder()
                .transferMoney(transferDtoRequest.getMoney())
                .dateTransfer(OffsetDateTime.now())
                .accountDetails(accountDetailsService.findAccountById(transferDtoRequest.getAccountDetailsId()))
                .operation(transferDtoRequest.getOperation())
                .build();
    }



}
