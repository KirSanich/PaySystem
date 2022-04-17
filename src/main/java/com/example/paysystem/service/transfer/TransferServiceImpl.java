package com.example.paysystem.service.transfer;

import com.example.paysystem.entity.AccountDetails;
import com.example.paysystem.entity.Transfer;
import com.example.paysystem.repository.TransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    @Autowired
    private final TransferRepository transferRepository;

    @Autowired
    private final Updatable balanceManager;

    @Override
    public List<Transfer> getAllTransfer() {
        log.info("Getting all transfers");
        return transferRepository.findAll();
    }

    @Override
    public void createTransfer(Transfer transfer) {
        log.info("Saving transfer..");
        transferRepository.save(transfer);
        balanceManager.updateAccountBalance(transfer);
    }




}
