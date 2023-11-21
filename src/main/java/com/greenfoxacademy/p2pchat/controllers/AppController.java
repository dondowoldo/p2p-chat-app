package com.greenfoxacademy.p2pchat.controllers;

import com.greenfoxacademy.p2pchat.dtos.ClientDTO;
import com.greenfoxacademy.p2pchat.dtos.MessageDTO;
import com.greenfoxacademy.p2pchat.dtos.PeerDTO;
import com.greenfoxacademy.p2pchat.models.Account;
import com.greenfoxacademy.p2pchat.models.Message;
import com.greenfoxacademy.p2pchat.services.AccountService;
import com.greenfoxacademy.p2pchat.services.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class AppController {

    private AccountService accountService;

    private MessageService messageService;
    private PeerDTO peer;
    private ClientDTO client;


    public AppController(AccountService accountService, MessageService messageService, PeerDTO peer, ClientDTO client) {
        this.accountService = accountService;
        this.messageService = messageService;
        this.peer = peer;
        this.client = client;
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
    public String postMessage(Model model, String text) {
        if (text == null || text.isEmpty()) {
            model.addAttribute("messages", messageService.findAllMessages());
            return "index";
        }
        String username = accountService.findAll().get(0).getUsername();
        Message message = new Message(username, text);
        messageService.saveMessage(message);
        messageService.postMessageObject(new MessageDTO(message, client), peer);
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
