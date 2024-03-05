package com.saas.plm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlmApplication {

	public static void main(String[] args) {
		System.out.println("printing");
		SpringApplication.run(PlmApplication.class, args);
	}

}
