package com.saas.plm.GptConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.saas.plm.GptConnect.controller")
public class GptConnectApplication {

	public static <ResponseEntity> void main(String[] args) {
//		ss
		SpringApplication.run(GptConnectApplication.class, args);
	}

}
