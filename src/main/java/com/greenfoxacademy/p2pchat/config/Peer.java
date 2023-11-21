package com.greenfoxacademy.p2pchat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Peer {
    @Value("${CHAT_APP_PEER_ADDRESS}")
    private String peerIp;
}
