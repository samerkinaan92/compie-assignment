package com.example.compieassignment.schedulingtasks;

import com.example.compieassignment.controllers.PlayersController;
import com.example.compieassignment.dto.Player;
import com.example.compieassignment.repositories.PlayerRepository;
import com.example.compieassignment.services.PlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private final PlayersService playersService;
    private final PlayerRepository playerRepository;

    public ScheduledTasks(final PlayersService playersService, PlayerRepository playerRepository) {
        this.playersService = playersService;
        this.playerRepository = playerRepository;
    }

    @Scheduled(fixedRate = 900000)
    public void updatePlayers() {
        log.info("Scheduled updatePlayers kicked in");
        boolean dirtyCache = false;
        List<Player> players = this.playersService.getPlayersList();
        for (Player player : players) {
            Player cachedPlayer = playerRepository.findById(Integer.toString(player.getId())).orElse(null);
            if (!player.equals(cachedPlayer)) {
                playerRepository.save(player);
                dirtyCache = true;
            }
        }

        if (dirtyCache) {
            log.info("update socket connections with new data");
            // updated socket
        }
    }
}
