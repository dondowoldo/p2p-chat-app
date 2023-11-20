package com.greenfoxacademy.p2pchat.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "account")
    private List<Message> messages;

    public Account(String username) {
        this.username = username;
    }
}
