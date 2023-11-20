package com.greenfoxacademy.p2pchat.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    @ManyToOne
    private Account account;
    private String text;
    private Timestamp timestamp;

    public Message() {
        this.timestamp = Timestamp.from(Instant.now());
    }

    public Message(Account account, String text) {
        this.account = account;
        this.text = text;
        this.timestamp = Timestamp.from(Instant.now());
    }
}
