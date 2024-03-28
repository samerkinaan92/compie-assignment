package com.example.compieassignment.controllers;

import com.example.compieassignment.services.PlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class PlayersController {

    private static final Logger log = LoggerFactory.getLogger(PlayersController.class);
    private final PlayersService playersService;

    public PlayersController(@Autowired PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping("/player")
    public String player() throws IOException {
        log.info("set players is called");
        File file  = playersService.getPlayers();
        return Files.readString(file.toPath());
    }
}
