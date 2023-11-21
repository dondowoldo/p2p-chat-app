package com.greenfoxacademy.p2pchat.controllers;

import com.greenfoxacademy.p2pchat.config.Client;
import com.greenfoxacademy.p2pchat.config.Peer;
import com.greenfoxacademy.p2pchat.dtos.MessageDTO;
import com.greenfoxacademy.p2pchat.models.Account;
import com.greenfoxacademy.p2pchat.models.Message;
import com.greenfoxacademy.p2pchat.services.AccountService;
import com.greenfoxacademy.p2pchat.services.MessageService;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AppController {

    private AccountService accountService;

    private MessageService messageService;
    private Peer peer;
    private Client client;


    public AppController(AccountService accountService, MessageService messageService, Peer peer, Client client) {
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
    public String postMessage(String text) {
        if (text == null || text.isEmpty()) {
            return "/";
        }
        String username = accountService.findAll().get(0).getUsername();
        Message message = new Message(username,text);
        messageService.saveMessage(message);

        MessageDTO messageDTO = new MessageDTO(message, client);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + peer.getPeerIp() + ":8080/api/message/receive";
        restTemplate.postForObject(url, new HttpEntity<MessageDTO>(messageDTO), MessageDTO.class);

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
