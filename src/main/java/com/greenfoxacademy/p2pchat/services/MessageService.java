package com.greenfoxacademy.p2pchat.services;

import com.greenfoxacademy.p2pchat.models.Message;
import com.greenfoxacademy.p2pchat.repositories.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> findAllMessages() {
        return messageRepo.findAll();
    }

    public Message saveMessage(Message message) {
        return messageRepo.save(message);
    }
}
