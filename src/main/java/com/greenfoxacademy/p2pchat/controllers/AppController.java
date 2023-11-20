package com.greenfoxacademy.p2pchat.controllers;

import com.greenfoxacademy.p2pchat.models.Account;
import com.greenfoxacademy.p2pchat.services.AccountService;
import com.greenfoxacademy.p2pchat.services.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Controller
public class AppController {

    private AccountService accountService;
    private MessageService messageService;

    public AppController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (accountService.findAll().isEmpty()) {
            return "redirect:/register";
        }
        model.addAttribute("messages", messageService.findAllMessages());
        return "index";
    }

    @PutMapping("/")
    public String index(Model model, String username) {
        Optional<Account> optionalCurrent = accountService.findByUsername(username);
        if (optionalCurrent.isEmpty()) {
            model.addAttribute("error", "Username not found");
            model.addAttribute("username", username);
            return "index";
        }
        AccountService.setCurrentAccount(optionalCurrent.get());
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        if (!accountService.findAll().isEmpty()) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, String username) {
        if (accountService.existsByUsername(username)) {
            model.addAttribute("username", username);
            model.addAttribute("error", "This username is already taken!");
            return "register";
        }
        AccountService.setCurrentAccount(accountService.saveUser(new Account(username)));
        return "redirect:/";
    }
}
