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
    private UUID id;
    private String username;
    private String text;
    private Timestamp timestamp;

    public Message() {
        this.timestamp = Timestamp.from(Instant.now());
        this.id = UUID.randomUUID();
    }

    public Message(String username, String text) {
        this.username = username;
        this.text = text;
        this.timestamp = Timestamp.from(Instant.now());
        this.id = UUID.randomUUID();
    }
    public Message(UUID id, String username, String text) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.timestamp = Timestamp.from(Instant.now());
    }

    public Message(UUID id, String username, String text, Timestamp timestamp) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.timestamp = timestamp;
    }
}
