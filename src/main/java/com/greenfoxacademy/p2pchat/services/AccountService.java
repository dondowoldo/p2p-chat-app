package com.greenfoxacademy.p2pchat.services;

import com.greenfoxacademy.p2pchat.models.Account;
import com.greenfoxacademy.p2pchat.repositories.AccountRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class AccountService {
    private AccountRepo accountRepo;
    @Getter
    @Setter
    private static Account currentAccount = null;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    public boolean existsByUsername(String username) {
        return accountRepo.existsByUsername(username);
    }

    public Account saveUser(Account account) {
        return accountRepo.save(account);
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepo.findByUsername(username);
    }
}
