package com.example.paysystem.controller;

import com.example.paysystem.dto.transfer.TransferDtoRequest;
import com.example.paysystem.dto.transfer.TransferDtoResponse;
import com.example.paysystem.entity.Transfer;
import com.example.paysystem.mapper.transfer.TransferMapper;
import com.example.paysystem.security.UserService;
import com.example.paysystem.service.transfer.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transfers")
@AllArgsConstructor
public class TransferController {

    @Autowired
    private final TransferService transferService;

    @Autowired
    private final TransferMapper transferMapper;

    @Autowired
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllTransfers() {

        List<TransferDtoResponse> transferList = transferService.getAllTransfer()
                .stream()
                .map(transferMapper::fromTransferToDtoResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(transferList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransfer(@RequestBody TransferDtoRequest transferDtoRequest, Authentication authentication) {

        Transfer transfer = transferMapper.fromTransferDtoRequestForCreateToTransfer(transferDtoRequest);
        userService.verifyId(authentication,transferDtoRequest.getAccountDetailsId());
        transferService.createTransfer(transfer);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

}
