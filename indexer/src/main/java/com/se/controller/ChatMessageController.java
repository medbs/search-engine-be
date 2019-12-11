package com.se.controller;

import com.se.interfaces.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ChatMessageController {


    @Autowired
    IChatMessageService ChatMessageService;

    @RequestMapping(value = "/index-chat", method = RequestMethod.POST)
    public ResponseEntity<?> getTrendsMongo() {
        HttpHeaders httpHeaders = new HttpHeaders();

        try {
            ChatMessageService.indexChatMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(
                httpHeaders,
                HttpStatus.OK
        );
    }

}
