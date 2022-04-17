package com.example.paysystem.service.transfer;

import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.entity.Transfer;

public interface Updatable {

    void updateAccountBalance(Transfer transfer);
}
