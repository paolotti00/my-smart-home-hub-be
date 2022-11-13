package com.paolotti.my.smart.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MySmartHomeApp {

	public static void main(String[] args) {
		SpringApplication.run(MySmartHomeApp.class, args);
	}

}
