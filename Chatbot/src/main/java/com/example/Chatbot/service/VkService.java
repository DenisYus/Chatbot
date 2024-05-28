package com.example.Chatbot.service;

import com.example.Chatbot.config.BotConfig;
import com.example.Chatbot.model.Message;
import com.example.Chatbot.model.VkRequest;
import com.example.Chatbot.util.VkApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class VkService {
    private static final Logger logger = LoggerFactory.getLogger(VkService.class);
    private final VkApiClient vkApiClient;
    private final ObjectMapper objectMapper;
    private final BotConfig botConfig;
    @Autowired
    public VkService(VkApiClient vkApiClient, ObjectMapper objectMapper, BotConfig botConfig) {
        this.vkApiClient = vkApiClient;
        this.objectMapper = objectMapper;
        this.botConfig = botConfig;
    }
    public String handleRequest(VkRequest request){
        logger.info("request: {}", request);
        logger.info("API token: {}", botConfig.getApiToken());
        if ("confirmation".equals(request.getType())){
            return botConfig.getConfirmationCode();
        } else if ("message_new".equals(request.getType())) {
            logger.info("New message");
            LinkedHashMap<String, Object> object = (LinkedHashMap<String, Object>) request.getObject();
            Message message = objectMapper.convertValue(object.get("message"), Message.class) ;
            String responseText = "Вы сказали:" + message.getText();
            logger.info("Sending response: {}", responseText);
            vkApiClient.sendMessage(message.getPeer_id(), responseText);
            return "ok";
        }
        return "ok";
    }
}
