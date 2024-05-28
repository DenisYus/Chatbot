package com.example.Chatbot.util;

import com.example.Chatbot.config.BotConfig;
import com.example.Chatbot.service.VkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class VkApiClient {
    private static final Logger logger = LoggerFactory.getLogger(VkService.class);
    private final BotConfig botConfig;
    @Autowired
    public VkApiClient(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    private final RestTemplate restTemplate = new RestTemplate();
    public void  sendMessage(int peerId, String message){
        String url = "https://api.vk.com/method/messages.send";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("message", message);
        params.add("peer_id", String.valueOf(peerId));
        params.add("random_id", String.valueOf(System.currentTimeMillis()));
        params.add("access_token", botConfig.getApiToken());
        params.add("v", botConfig.getApiVersion());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params,headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String responseBody = response.getBody();
        logger.info("Response from Vk: {}", responseBody);
    }

}
