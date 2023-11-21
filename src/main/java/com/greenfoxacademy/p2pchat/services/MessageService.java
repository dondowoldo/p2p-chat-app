package com.greenfoxacademy.p2pchat.services;

import com.greenfoxacademy.p2pchat.dtos.MessageDTO;
import com.greenfoxacademy.p2pchat.models.Message;
import com.greenfoxacademy.p2pchat.repositories.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public boolean existsById(UUID uuid) {
        return messageRepo.existsById(uuid);
    }

    public String validateDTO(MessageDTO messageDTO) {

        List<String> messages = new ArrayList<>();

        if (messageDTO.message().getId() == null) {
            messages.add("message.id");
        }
        if (messageDTO.message().getUsername() == null || messageDTO.message().getUsername().isEmpty()) {
            messages.add("message.username");
        }
        if (messageDTO.message().getText() == null || messageDTO.message().getText().isEmpty()) {
            messages.add("message.text");
        }
        if (messageDTO.message().getTimestamp() == null) {
            messages.add("message.timestamp");
        }
        if (messageDTO.client().id() == null || messageDTO.client().id().isEmpty()) {
            messages.add("client.id");
        }
        if (messages.isEmpty()) {
            return null;
        }
        return "Missing field(s): " + String.join(", ", messages);
    }


}
