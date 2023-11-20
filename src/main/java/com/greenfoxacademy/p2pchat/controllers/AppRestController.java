package com.greenfoxacademy.p2pchat.controllers;

import com.greenfoxacademy.p2pchat.dtos.ErrorDTO;
import com.greenfoxacademy.p2pchat.dtos.MessageDTO;
import com.greenfoxacademy.p2pchat.services.ApiService;
import com.greenfoxacademy.p2pchat.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {
    private ApiService apiService;
    private final MessageService messageService;

    public AppRestController(ApiService apiService, MessageService messageService) {
        this.apiService = apiService;
        this.messageService = messageService;
    }

    @PostMapping("/api/message/receive")
    public ResponseEntity<Object> receiveMsg(@RequestBody MessageDTO messageDTO) {
        String message = messageService.validateDTO(messageDTO);
        if (message != null) {
            return ResponseEntity.status(401).body(new ErrorDTO("error", message));
        }
        if (!messageService.existsById(messageDTO.message().getId())) {
            messageService.saveMessage(messageDTO.message());
            return ResponseEntity.status(201).build();
        }
        apiService.receiveMessage(messageDTO);
        return ResponseEntity.ok().build();
    }
}
