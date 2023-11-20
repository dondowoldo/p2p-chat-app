package com.greenfoxacademy.p2pchat.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
@Getter
@Setter
@NoArgsConstructor
public class Client {
//    @Value("${CHAT_APP_UNIQUE_ID}")
    private String id;
}
