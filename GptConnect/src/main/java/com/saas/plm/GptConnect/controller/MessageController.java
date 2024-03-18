package com.saas.plm.GptConnect.controller;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {
    @PostMapping("/api/messages/send")
    public String sendMessageToChatGPT(@RequestBody String message) {
        // Replace 'YOUR_OPENAI_API_KEY' with your actual OpenAI API key
        String openaiApiKey = "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";
        String openaiApiUrl = "https://api.openai.com/v1/chat/completions";

        RestTemplate restTemplate = new RestTemplate();

        System.out.println(message);
        JSONObject jsonObject = new JSONObject(message);
        String msg = jsonObject.getString("message");
        String requestData = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + msg +"\"}]}";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.set("Content-Type", "application/json");

        // Make a POST request to the OpenAI API
        HttpEntity<String> request = new HttpEntity<>(requestData, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(openaiApiUrl, request, String.class);
        System.out.println(response);
        // Extract and return response from OpenAI
        JSONObject responseObject = new JSONObject(response.getBody()) ;
        return responseObject.getJSONArray("choices").getJSONObject(0)
                .getJSONObject("message").getString("content");
    }
}
