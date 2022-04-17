package com.example.paysystem.service.transfer;

import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.entity.Transfer;

import java.util.List;

public interface TransferService {

    List<Transfer> getAllTransfer();

    void createTransfer(Transfer transfer);

}
