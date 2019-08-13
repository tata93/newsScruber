package com.example.tochka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TochkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TochkaApplication.class, args);
	}

}