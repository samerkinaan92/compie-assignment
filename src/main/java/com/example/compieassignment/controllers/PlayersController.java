package com.example.compieassignment.controllers;

import com.example.compieassignment.services.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayersController {

    private final PlayersService playersService;

    public PlayersController(@Autowired PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping("/player")
    public String player() {
        return playersService.getPlayers();
    }
}
