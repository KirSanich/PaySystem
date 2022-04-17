package com.example.paysystem.controller;


import com.example.paysystem.dto.accountdetails.AccountDetailsDtoRequest;
import com.example.paysystem.dto.accountdetails.AccountDetailsDtoResponse;
import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.mapper.accountdetails.AccountDetailsMapper;
import com.example.paysystem.service.accountdetails.AccountDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountDetailsController {

    @Autowired
    private final AccountDetailsMapper accountDetailsMapper;

    @Autowired
    private final com.example.paysystem.security.UserService userSecurityService;

    @Autowired
    private final AccountDetailsService accountDetailsService;

    @GetMapping("")
    public ResponseEntity<?> getAllAccountDetails() {
        List<AccountDetailsDtoResponse> accountDetailsList = accountDetailsService.getAllAccountDetails()
                .stream().map(accountDetailsMapper::fromAccountDetailsToDtoResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountDetailsList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDetailsDtoRequest accountDetailsDtoRequest, Authentication authentication) {
        AccountDetails accountDetails = accountDetailsMapper.fromAccountDetailsDtoForCreateToAccountDetails(accountDetailsDtoRequest);
        userSecurityService.verifyId(authentication,accountDetailsDtoRequest.getUserId());
        accountDetailsService.createAccount(accountDetails);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id, Authentication authentication) {
        userSecurityService.verifyId(authentication,id);
        AccountDetailsDtoResponse accountDetailsDtoResponse = accountDetailsMapper.fromAccountDetailsToDtoResponse(accountDetailsService.findAccountById(id));
        return new ResponseEntity<>(accountDetailsDtoResponse, HttpStatus.FOUND);
    }
}
