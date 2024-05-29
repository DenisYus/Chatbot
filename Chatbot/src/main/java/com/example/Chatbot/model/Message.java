package com.example.Chatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Message {
    private String text;
    @JsonProperty("from_id")
    private int fromId;
    private int id;
    @JsonProperty("peer_id")
    private int peerId;
}
