package com.greenfoxacademy.p2pchat.services;

import com.greenfoxacademy.p2pchat.repositories.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
}
