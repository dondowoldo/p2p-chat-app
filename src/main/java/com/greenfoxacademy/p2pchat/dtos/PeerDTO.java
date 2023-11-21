package com.greenfoxacademy.p2pchat.dtos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public record PeerDTO(@Value("${CHAT_APP_PEER_ADDRESS}") String peerIp) {}