package com.example.paysystem.mapper.transfer;

import com.example.paysystem.dto.TransferDTO;
import com.example.paysystem.entity.Transfer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public TransferMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransferDTO fromTransferToDTO(Transfer transfer)
    {
        return TransferDTO.builder()
                .id(transfer.getId())
                .money(transfer.getTransferMoney())
                .date(transfer.getDateTransfer())
                .build();
    }
}
