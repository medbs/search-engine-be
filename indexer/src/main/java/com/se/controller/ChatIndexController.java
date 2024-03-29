package com.se.controller;

import com.se.dto.ResponseDto;
import com.se.interfaces.IChatIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ChatIndexController {


    @Autowired
    IChatIndexService ChatMessageService;

    @RequestMapping(value = "/index-chat", method = RequestMethod.POST)
    public ResponseEntity<?> getTrendsMongo() {
        HttpHeaders httpHeaders = new HttpHeaders();

        ResponseDto<?> response = ChatMessageService.indexChatMessages();
        return new ResponseEntity<>(
                response,
                httpHeaders,
                response.getStatus()
        );

    }

}
