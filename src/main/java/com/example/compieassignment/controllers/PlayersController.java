package com.example.compieassignment.controllers;

import com.example.compieassignment.services.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class PlayersController {

    private final PlayersService playersService;

    public PlayersController(@Autowired PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping("/player")
    public String player() throws IOException {
        File file  = playersService.getPlayers();
        return Files.readString(file.toPath());
    }

    @MessageMapping("/players")
    @SendTo("/topic/messages")
    public String send(String message) throws Exception {
		return message;
    }
}
