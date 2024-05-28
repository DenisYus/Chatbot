package com.example.Chatbot.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VkResponse {

    private String response;
    public  VkResponse (String response){
        this.response = response;
    }


}
