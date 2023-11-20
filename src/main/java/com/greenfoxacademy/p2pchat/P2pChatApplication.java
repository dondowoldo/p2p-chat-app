package com.greenfoxacademy.p2pchat;

import com.greenfoxacademy.p2pchat.models.Account;
import com.greenfoxacademy.p2pchat.models.Message;
import com.greenfoxacademy.p2pchat.repositories.AccountRepo;
import com.greenfoxacademy.p2pchat.repositories.MessageRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class P2pChatApplication implements CommandLineRunner {
	private AccountRepo accountRepo;

	private MessageRepo messageRepo;

	public P2pChatApplication(AccountRepo accountRepo, MessageRepo messageRepo) {
		this.accountRepo = accountRepo;
		this.messageRepo = messageRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(P2pChatApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Account account = new Account("Username");
//
//		Message m1 = new Message("retard", "blahblah");
//		Message m2 = new Message("kokon", "blahblahblah");
//		Message m3 = new Message(account.getUsername(), "blahblahblahbah");
//
//		accountRepo.save(account);
//		messageRepo.save(m1);
//		messageRepo.save(m2);
//		messageRepo.save(m3);
//
//		System.out.println(m1.getId());
//		System.out.println(m2.getId());
//		System.out.println(m3.getId());
//
	}
}
