package com.example.paysystem.service.accountdetails;

import com.example.paysystem.entity.AccountDetails;

import java.util.List;

public interface AccountDetailsService {
    List<AccountDetails> getAllAccountDetails();

    void createAccount(AccountDetails accountDetails);

    AccountDetails findAccountById(Long id);

    void updateAccountDetails(AccountDetails accountDetails);
}
