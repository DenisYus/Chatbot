package com.example.Chatbot.controller;

import com.example.Chatbot.model.VkRequest;
import com.example.Chatbot.service.VkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class VkController {
    private final VkService vkService;

    @PostMapping
    private ResponseEntity<String> receiveEvent(@RequestBody VkRequest request) {
        return ResponseEntity.ok(vkService.handleRequest(request));
    }
}
