package com.greenfoxacademy.p2pchat.repositories;

import com.greenfoxacademy.p2pchat.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepo extends JpaRepository<Message, Long> {
    Page<Message> findAllByOrderByTimestampAsc(Pageable pageable);
    Page<Message> findAllByOrderByTimestampDesc(Pageable pageable);
}