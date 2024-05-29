package com.example.Chatbot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum VkRequestType {
    CONFIRMATION("confirmation"),
    MESSAGE_NEW("message_new");
    private final String type;

    @JsonValue
    public String getType() {
        return type;
    }

    @JsonCreator
    public static VkRequestType fromString(String type) {
        for (VkRequestType requestType : VkRequestType.values()) {
            if (requestType.type.equalsIgnoreCase(type)) {
                return requestType;
            }
        }
        throw new IllegalArgumentException("Bad request type" + type);
    }
}
