package com.example.Chatbot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private  String text;
    private int from_id;
    private int id;
    private int peer_id;
}
