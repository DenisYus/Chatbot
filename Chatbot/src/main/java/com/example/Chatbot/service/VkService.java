package com.example.Chatbot.service;

import com.example.Chatbot.config.BotConfig;
import com.example.Chatbot.model.Message;
import com.example.Chatbot.model.VkRequest;
import com.example.Chatbot.util.VkApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VkService {
    private final VkApiClient vkApiClient;
    private final BotConfig botConfig;
    @Autowired
    public VkService(VkApiClient vkApiClient, BotConfig botConfig) {
        this.vkApiClient = vkApiClient;
        this.botConfig = botConfig;
    }
    public String handleRequest(VkRequest request){
        if ("confirmation".equals(request.getType())){
            return botConfig.getConfirmationCode();
        } else if ("message_new".equals(request.getType())) {
            Message message = (Message) request.getObject();
            String responseText = "Вы сказали:" + message.getText();
            vkApiClient.sendMessage(message.getPeer_id(), responseText);
            return "ok";
        }
        return "ok";
    }
}
