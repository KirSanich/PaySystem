package com.example.paysystem.service.transfer;

import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.entity.Transfer;
import com.example.paysystem.exception.transfer.NoMoneyOnBalance;
import com.example.paysystem.service.accountdetails.AccountDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class BalanceManager implements Updatable {

    @Autowired
    private final AccountDetailsService accountDetailsService;

    @Override
    public synchronized void updateAccountBalance(Transfer transfer) {

        AccountDetails accountDetails = accountDetailsService.findAccountById(transfer.getAccountDetails().getId());
        BigDecimal balanceAccount = accountDetails.getMoney();
        String type = transfer.getOperation().getAbbreviation();
        switch (type)
        {
            case "up" -> accountDetails.setMoney(balanceAccount.add(transfer.getTransferMoney()));
            case "down" ->
                    {
                        if((balanceAccount.compareTo(transfer.getTransferMoney()) > 0)) {
                            accountDetails.setMoney(balanceAccount.subtract(transfer.getTransferMoney()));
                        }
                        else throw new NoMoneyOnBalance("No money on balance for this operation");
                    }
            default -> throw new UnsupportedOperationException("Wrong operation");
        }
        accountDetailsService.updateAccountDetails(accountDetails);
    }
}
