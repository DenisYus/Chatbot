package com.example.Chatbot.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VkRequest {
    private String type;
    private Object object;
    private String group_id;
    private String secret;
}
