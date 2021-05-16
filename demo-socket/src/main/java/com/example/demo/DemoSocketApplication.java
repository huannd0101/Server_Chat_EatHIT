package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.socket.SocketApplication;

@SpringBootApplication
public class DemoSocketApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoSocketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		SocketApplication.startSocketApplication();
	}

}
