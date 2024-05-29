package com.example.Chatbot.service;

import com.example.Chatbot.config.BotConfig;
import com.example.Chatbot.exception.BadRequestType;
import com.example.Chatbot.model.Message;
import com.example.Chatbot.model.VkRequest;
import com.example.Chatbot.model.VkRequestType;
import com.example.Chatbot.util.VkApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;


@Service
@RequiredArgsConstructor
public class VkService {
    @Value("${response.template}")
    private String responseTemplate;
    private static final Logger logger = LoggerFactory.getLogger(VkService.class);
    private final VkApiClient vkApiClient;
    private final ObjectMapper objectMapper;
    private final BotConfig botConfig;

    public String handleRequest(VkRequest request) {
        VkRequestType requestType = request.getType();
        switch (requestType) {
            case CONFIRMATION -> {
                return botConfig.getConfirmationCode();
            }
            case MESSAGE_NEW -> {
                return handleMessageNew(request);
            }
            default -> throw new BadRequestType(String.format("Bad request type: %s", requestType));
        }
    }


    public String handleMessageNew(VkRequest request) {
        final String STATUS_OK = "ok";
        final String GET_MESSAGE = "message";
        logger.info("request: {}", request);
        var requestObject = (LinkedHashMap<String, Object>) request.getObject();
        Message message = objectMapper.convertValue(requestObject.get(GET_MESSAGE), Message.class);
        String responseText = String.format(responseTemplate, message.getText());
        logger.info("Sending response: {}", responseText);
        vkApiClient.sendMessage(message.getPeerId(), responseText);
        return STATUS_OK;

    }
}
