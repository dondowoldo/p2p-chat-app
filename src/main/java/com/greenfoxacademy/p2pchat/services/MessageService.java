package com.greenfoxacademy.p2pchat.services;

import com.greenfoxacademy.p2pchat.repositories.MessageRepo;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }
}
