package com.example.Chatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class VkRequest {
    private VkRequestType type;
    private Object object;
    @JsonProperty("group_id")
    private String groupId;
    private String secret;
}
