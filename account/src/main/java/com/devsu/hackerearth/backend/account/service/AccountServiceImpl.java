package com.devsu.hackerearth.backend.account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDto> getAll() {
        // Get all accounts
        return accountRepository.findAll()
                .stream()
                .map(a -> new AccountDto(
                        a.getId(),
                        a.getNumber(),
                        a.getType(),
                        a.getInitialAmount(),
                        a.isActive(),
                        a.getClientId()))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        // Get accounts by id
        AccountDto acount = null;
        Account a = accountRepository.findById(id).orElse(null);
        if (a != null) {
            acount = new AccountDto(
                    a.getId(),
                    a.getNumber(),
                    a.getType(),
                    a.getInitialAmount(),
                    a.isActive(),
                    a.getClientId());

        }
        return acount;
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        // Create account
        if (accountDto != null && accountDto.getId() == 0) {
            Account a = new Account();
            a.setActive(accountDto.isActive());
            a.setClientId(accountDto.getClientId());
            a.setId(accountDto.getId());
            a.setInitialAmount(accountDto.getInitialAmount());
            a.setNumber(accountDto.getNumber());
            a.setType(accountDto.getType());
            accountRepository.save(a);
            accountDto.setId(a.getId());
        }
        return accountDto;
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        // Update account
        if (accountDto != null && accountDto.getId() > 0) {
            Account a = new Account();
            a.setActive(accountDto.isActive());
            a.setClientId(accountDto.getClientId());
            a.setId(accountDto.getId());
            a.setInitialAmount(accountDto.getInitialAmount());
            a.setNumber(accountDto.getNumber());
            a.setType(accountDto.getType());
            accountRepository.save(a);
            accountDto.setId(a.getId());
        }
        return accountDto;
    }

    @Override
    public AccountDto partialUpdate(Long id, PartialAccountDto partialAccountDto) {
        AccountDto acount = null;
        Account a = accountRepository.findById(id).orElse(null);
        if (a != null) {
            a.setActive(partialAccountDto.isActive());
            accountRepository.save(a);
            acount = new AccountDto(
                    a.getId(),
                    a.getNumber(),
                    a.getType(),
                    a.getInitialAmount(),
                    a.isActive(),
                    a.getClientId());

        }
        return acount;
    }

    @Override
    public void deleteById(Long id) {
        // Delete account
        accountRepository.deleteById(id);
    }

}
