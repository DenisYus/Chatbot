package com.example.Chatbot.controller;

import com.example.Chatbot.model.VkRequest;
import com.example.Chatbot.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class VkController {
    private final VkService vkService;
    @Autowired
    public VkController(VkService vkService) {
        this.vkService = vkService;
    }
    @PostMapping
    private  String receiveEvent(@RequestBody VkRequest request){
        return vkService.handleRequest(request);
    }
}
