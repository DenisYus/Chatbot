package com.example.Chatbot.model;

import com.example.Chatbot.exception.BadRequestTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
        throw new BadRequestTypeException(String.format("Bad request type: %s", type));
    }
}
