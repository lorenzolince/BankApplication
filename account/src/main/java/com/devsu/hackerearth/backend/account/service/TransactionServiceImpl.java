package com.devsu.hackerearth.backend.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;
import com.devsu.hackerearth.backend.account.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<TransactionDto> getAll() {
        // Get all transactions
        return transactionRepository.findAll().stream()
                .map(t -> new TransactionDto(
                        t.getId(),
                        t.getDate(),
                        t.getType(),
                        t.getAmount(),
                        t.getBalance(),
                        t.getAccountId()))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto getById(Long id) {
        // Get transactions by id
        TransactionDto transaction = null;
        Transaction t = transactionRepository.findById(id).orElse(null);
        if (t != null) {
            transaction = new TransactionDto(
                    t.getId(),
                    t.getDate(),
                    t.getType(),
                    t.getAmount(),
                    t.getBalance(),
                    t.getAccountId());
        }
        return transaction;
    }

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        // Create transaction
        Transaction t = new Transaction();
        if (transactionDto != null && transactionDto.getId() == 0) {
            t.setAccountId(transactionDto.getAccountId());
            t.setAmount(transactionDto.getAmount());
            t.setBalance(transactionDto.getBalance());
            t.setDate(transactionDto.getDate());
            t.setId(transactionDto.getId());
            t.setType(transactionDto.getType());
            transactionRepository.save(t);
            transactionDto.setId(t.getId());
        }
        return transactionDto;
    }

    @Override
    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart,
            Date dateTransactionEnd) {
        // Report
        List<BankStatementDto> listBanks = new ArrayList<>();
        List<Transaction> tList = transactionRepository.findByDateBetween(dateTransactionStart, dateTransactionStart);
        Account acount = accountRepository.findFirstByClientId(clientId);
        for (Transaction t : tList) {
            listBanks.add(new BankStatementDto(
                    t.getDate(),
                    "name Client",
                    acount.getNumber(),
                    acount.getType(),
                    acount.getInitialAmount(),
                    acount.isActive(),
                    t.getType(),
                    t.getAmount(),
                    t.getBalance()));
        }
        return listBanks;
    }

    @Override
    public TransactionDto getLastByAccountId(Long accountId) {
        // If you need it
        TransactionDto transaction = null;
        Transaction t = transactionRepository.findTopByAccountIdOrderByDateDesc(accountId);
        if (t != null) {
            transaction = new TransactionDto(
                    t.getId(),
                    t.getDate(),
                    t.getType(),
                    t.getAmount(),
                    t.getBalance(),
                    t.getAccountId());
        }
        return transaction;
    }

}
