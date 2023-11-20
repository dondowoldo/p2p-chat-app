package com.greenfoxacademy.p2pchat.services;

import com.greenfoxacademy.p2pchat.dtos.MessageDTO;
import com.greenfoxacademy.p2pchat.models.Account;
import com.greenfoxacademy.p2pchat.models.Message;
import com.greenfoxacademy.p2pchat.repositories.AccountRepo;
import com.greenfoxacademy.p2pchat.repositories.MessageRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class ApiService {

    private MessageRepo messageRepo;
    private AccountRepo accountRepo;

    public ApiService(MessageRepo messageRepo, AccountRepo accountRepo) {
        this.messageRepo = messageRepo;
        this.accountRepo = accountRepo;
    }

    public void receiveMessage(MessageDTO messageDTO) {
        Account account = accountRepo.save(new Account(messageDTO.client().id()));
        Message message = messageRepo.save(new Message(
                messageDTO.message().getId(),
                account.getUsername(),
                messageDTO.message().getText(),
                messageDTO.message().getTimestamp())
        );
    }


}
