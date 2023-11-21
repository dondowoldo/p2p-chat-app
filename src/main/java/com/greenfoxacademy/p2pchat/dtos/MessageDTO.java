package com.greenfoxacademy.p2pchat.dtos;

import com.greenfoxacademy.p2pchat.config.Client;
import com.greenfoxacademy.p2pchat.models.Message;

public record MessageDTO(Message message, Client client) {
}
