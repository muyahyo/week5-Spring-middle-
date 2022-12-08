package com.sparta.homework4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Homework4Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework4Application.class, args);
	}

}
