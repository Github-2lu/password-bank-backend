package com.tulu.passwordms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PasswordmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordmsApplication.class, args);
	}

}
