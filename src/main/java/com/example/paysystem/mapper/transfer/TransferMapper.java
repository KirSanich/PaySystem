package com.example.paysystem.mapper.transfer;

import com.example.paysystem.dto.transfer.TransferDTO;
import com.example.paysystem.entity.Transfer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransferMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public TransferDTO fromTransferToDTO(Transfer transfer)
    {
        return TransferDTO.builder()
                .id(transfer.getId())
                .money(transfer.getTransferMoney())
                .date(transfer.getDateTransfer())
                .build();
    }
}
