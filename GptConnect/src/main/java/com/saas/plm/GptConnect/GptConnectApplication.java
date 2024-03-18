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
//		String openaiApiKey = "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";
//		String openaiApiUrl = "https://api.openai.com/v1/engines";
//
//		RestTemplate restTemplate = new RestTemplate();
//		String authHeader = "Bearer " + openaiApiKey;
//
//		// Set headers
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", authHeader);
//		headers.set("Content-Type", "application/json");
//
//		// Make a GET request to the OpenAI API to fetch available models
//		HttpEntity<String> request = new HttpEntity<>(headers);
//		org.springframework.http.ResponseEntity<String> response = restTemplate.exchange(openaiApiUrl, HttpMethod.GET, request, String.class);
//
//		// Print response body
//		System.out.println(response.getBody());
		SpringApplication.run(GptConnectApplication.class, args);
	}

}
