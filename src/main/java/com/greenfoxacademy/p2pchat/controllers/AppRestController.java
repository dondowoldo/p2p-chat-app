package com.greenfoxacademy.p2pchat.controllers;

import com.greenfoxacademy.p2pchat.dtos.ClientDTO;
import com.greenfoxacademy.p2pchat.dtos.ErrorDTO;
import com.greenfoxacademy.p2pchat.dtos.MessageDTO;
import com.greenfoxacademy.p2pchat.dtos.PeerDTO;
import com.greenfoxacademy.p2pchat.services.AccountService;
import com.greenfoxacademy.p2pchat.services.MessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AppRestController {
    private MessageService messageService;
    private AccountService accountService;
    private ClientDTO client;
    private PeerDTO peer;

    public AppRestController(MessageService messageService, AccountService accountService, ClientDTO client, PeerDTO peer) {
        this.messageService = messageService;
        this.accountService = accountService;
        this.client = client;
        this.peer = peer;
    }

    @PostMapping("/api/message/receive")
    public ResponseEntity<Object> receiveMsg(@RequestBody MessageDTO messageDTO) {
        String message = messageService.validateDTO(messageDTO);
        if (message != null) {
            return ResponseEntity.status(401).body(new ErrorDTO("error", message));
        }
        if (!messageService.existsById(messageDTO.message().getId())) {
            messageService.saveMessage(messageDTO.message());
        }
        if (!messageDTO.client().id().equals(client.id())) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://" + peer.peerIp() + ":8080/api/message/receive";
            restTemplate.postForObject(url, new HttpEntity<>(messageDTO), MessageDTO.class);
        }
        return ResponseEntity.status(201).build();
    }
}
