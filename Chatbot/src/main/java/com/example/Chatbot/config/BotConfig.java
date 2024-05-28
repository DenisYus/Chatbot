package com.example.Chatbot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class BotConfig {
    @Value("${vk.api.token}")
    private  String apiToken;
    @Value("${vk.confirmation.code}")
    private String confirmationCode;
    @Value("${vk.api.version}")
    private String apiVersion;
    @Value("${vk.group.id}")
    private String groupId;
}
