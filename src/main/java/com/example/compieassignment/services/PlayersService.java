package com.example.compieassignment.services;

import com.example.compieassignment.dto.Player;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayersService {

    private final String baseUrl = "https://api.balldontlie.io";

    @Value("${balldontlie.api.key}")
    private final String apiKey = "";

    @Value("classpath:data/players.csv")
    private Resource resourceFile;

    public String getPlayers() {
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(resourceFile.getFile()))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            getPlayersFromApi(Arrays.asList(1L, 2L));
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    private List<Player> getPlayersFromApi(List<Long> ids) {
        WebClient webClient = WebClient.create();

        // Set up the API endpoint
        String apiUrl = "https://api.balldontlie.io/v1/players?player_ids[]=1&player_ids[]=2";

        // Make the request
        String response = webClient.get()
                .uri(apiUrl)
                .header("Authorization", apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Print the response
        System.out.println(response);


        return new ArrayList<Player>();
    }

}
