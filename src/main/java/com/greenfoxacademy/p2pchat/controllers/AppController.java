package com.greenfoxacademy.p2pchat.controllers;

import com.greenfoxacademy.p2pchat.models.Account;
import com.greenfoxacademy.p2pchat.services.AccountService;
import com.greenfoxacademy.p2pchat.services.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    private AccountService accountService;
    private MessageService messageService;

    public AppController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String index() {
        if (accountService.findAll().isEmpty()) {
            return "redirect:/register";
        }
        return "index";
    }

    @GetMapping("/register")
    public String register() {
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
