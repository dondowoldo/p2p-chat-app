package com.greenfoxacademy.p2pchat.dtos;

import com.greenfoxacademy.p2pchat.models.Message;

public record MessageDTO(Message message, ClientDTO client) {
}