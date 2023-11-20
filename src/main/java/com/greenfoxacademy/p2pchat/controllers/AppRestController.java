package com.greenfoxacademy.p2pchat.controllers;

import com.greenfoxacademy.p2pchat.dtos.MessageDTO;
import com.greenfoxacademy.p2pchat.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {
    private final MessageService messageService;

    public AppRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/api/message/receive")
    public ResponseEntity<Object> receiveMsg(MessageDTO messageDTO) {
        if (messageService.existsById(messageDTO.message().getId())) {
//            nasrat
        }
        messageService.saveMessage(messageDTO.message());
        return ResponseEntity.status(201).build();

    }
}
