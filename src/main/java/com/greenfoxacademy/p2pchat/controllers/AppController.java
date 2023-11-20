package com.greenfoxacademy.p2pchat.controllers;

import com.greenfoxacademy.p2pchat.models.Account;
import com.greenfoxacademy.p2pchat.models.Message;
import com.greenfoxacademy.p2pchat.services.AccountService;
import com.greenfoxacademy.p2pchat.services.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
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
    public String index(String username) {
        Account account = accountService.findAll().get(0);
        if (account == null) {
            return "redirect:/register";
        }
        account.setUsername(username);
        accountService.saveUser(account);
        return "redirect:/";
    }

    @PostMapping("/")
    public String postMessage(String text) {
        if (text == null || text.isEmpty()) {
            return "/";
        }
        String username = accountService.findAll().get(0).getUsername();
        Message message = new Message(username,text);
        messageService.saveMessage(message);
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
        if (username == null || username.isEmpty()) {
            model.addAttribute("error", "The username field is empty");
            return "register";
        }
        accountService.saveUser(new Account(username));
        return "redirect:/";
    }
}
