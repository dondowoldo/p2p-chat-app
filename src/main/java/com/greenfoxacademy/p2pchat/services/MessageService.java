package com.greenfoxacademy.p2pchat.services;

import com.greenfoxacademy.p2pchat.dtos.MessageDTO;
import com.greenfoxacademy.p2pchat.dtos.PeerDTO;
import com.greenfoxacademy.p2pchat.models.Message;
import com.greenfoxacademy.p2pchat.repositories.MessageRepo;
import com.greenfoxacademy.p2pchat.utils.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.greenfoxacademy.p2pchat.utils.Settings.currentSortType;

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

    public boolean existsById(Long id) {
        return messageRepo.existsById(id);
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

    public void postMessageObject(MessageDTO messageDTO, PeerDTO peer) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + peer.peerIp() + ":8080/api/message/receive";
        try {
            restTemplate.postForObject(url, new HttpEntity<>(messageDTO), MessageDTO.class);
            System.out.println("Message sent");
        } catch (ResourceAccessException | HttpClientErrorException ignored) {
        }
    }

    public Page<Message> findPageSort(int pageNumber, int pageSize, SortType sortType) {
        if (currentSortType.equals(SortType.TIME_ASCENDING)) {
            return findAllByOrderByCreatedAtAsc(pageNumber, pageSize);
        } else if (currentSortType.equals(SortType.TIME_DESCENDING)) {
            return findAllByOrderByCreatedAtDesc(pageNumber, pageSize);
        }  else {
            throw new EnumConstantNotPresentException(SortType.class, "Non-existing SortType value!");
        }
    }

    public Page<Message> findAllByOrderByCreatedAtAsc(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("timestamp").ascending());
        return messageRepo.findAll(pageRequest);
    }

    public Page<Message> findAllByOrderByCreatedAtDesc(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("timestamp").descending());
        return messageRepo.findAll(pageRequest);
    }
}