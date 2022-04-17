package com.example.paysystem.service.accountdetails;


import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.exception.accountdetails.AccountDetailsWithCurrentIdNotFound;
import com.example.paysystem.repository.AccountDetailsRepository;
import com.example.paysystem.service.accountdetails.AccountDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AccountDetailsServiceImpl implements AccountDetailsService {

    @Autowired
    private final AccountDetailsRepository accountDetailsRepository;

    @Override
    public List<AccountDetails> getAllAccountDetails() {
        log.info("Getting all account details");
        return accountDetailsRepository.findAll();
    }

    @Override
    public void createAccount(AccountDetails accountDetails) {
        log.info("Creating new Account");
        accountDetailsRepository.save(accountDetails);
    }

    @Override
    public AccountDetails findAccountById(Long id) {
        log.info("Searching account with id:{}", id);
        return accountDetailsRepository.findById(id).orElseThrow(() -> new AccountDetailsWithCurrentIdNotFound("No found account with id " + id));
    }

    @Override
    public void updateAccountDetails(AccountDetails accountDetails) {
        log.info("Updating account details");
        accountDetailsRepository.save(accountDetails);
    }
}
