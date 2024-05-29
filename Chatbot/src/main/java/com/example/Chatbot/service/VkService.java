package com.example.Chatbot.service;

import com.example.Chatbot.config.BotConfig;
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

    public ResponseEntity<?> handleRequest(VkRequest request) {
        VkRequestType requestType = request.getType();
        switch (requestType) {
            case CONFIRMATION -> {
                return ResponseEntity.ok(botConfig.getConfirmationCode());
            }
            case MESSAGE_NEW -> {
                return handleMessageNew(request);
            }
            default -> throw new IllegalArgumentException("Bad request type" + requestType);
        }
    }


    public ResponseEntity<?> handleMessageNew(VkRequest request) {

        final String STATUS_OK = "ok";
        final String GET_MESSAGE = "message";
        logger.info("request: {}", request);
        logger.info("New message");
        var requestObject = (LinkedHashMap<String, Object>) request.getObject();
        Message message = objectMapper.convertValue(requestObject.get(GET_MESSAGE), Message.class);
        String responseText = String.format(responseTemplate, message.getText());
        logger.info("Sending response: {}", responseText);
        vkApiClient.sendMessage(message.getPeerId(), responseText);
        return ResponseEntity.ok(STATUS_OK);

    }
}
