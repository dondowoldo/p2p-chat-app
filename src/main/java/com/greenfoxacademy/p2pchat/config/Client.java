package com.greenfoxacademy.p2pchat.config;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class Client {
    @Value("${CHAT_APP_UNIQUE_ID}")
    private String id;
}