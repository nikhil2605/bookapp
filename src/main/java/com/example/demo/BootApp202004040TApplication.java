package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
@SpringBootApplication
public class BootApp202004040TApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApp202004040TApplication.class, args);
	}

}
