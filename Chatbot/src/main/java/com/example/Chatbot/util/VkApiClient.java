package com.example.Chatbot.util;

import com.example.Chatbot.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class VkApiClient {
    private final BotConfig botConfig;
    @Autowired
    public VkApiClient(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    private final RestTemplate restTemplate = new RestTemplate();
    public void  sendMessage(int peerId, String message){
        String url = "https://api.vk.com/method/messages.send";
        Map<String, String> params = new HashMap<>();
        params.put("message", message);
        params.put("peer_id", String.valueOf(peerId));
        params.put("random_id", String.valueOf(System.currentTimeMillis()));
        params.put("access_token", botConfig.getApiToken());
        params.put("v", botConfig.getApiVersion());
        restTemplate.postForObject(url, null, String.class, params);
    }

}
