package com.example.compieassignment.services;

import com.example.compieassignment.dto.Player;
import com.example.compieassignment.dto.PlayersDto;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class PlayersService {
    private static final Logger log = LoggerFactory.getLogger(PlayersService.class);
    private final String baseUrl = "https://api.balldontlie.io/v1/players";

    @Value("${balldontlie.api.key}")
    private String apiKey;

    @Value("classpath:data/players.csv")
    private Resource resourceFile;

    public File getPlayers() {
        List<Long> ids = new ArrayList<>();
        File playersCSV;
        try (CSVReader csvReader = new CSVReader(new FileReader(resourceFile.getFile()))) {
            String[] values;
            String[] keys = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                Long id = Long.parseLong(getMap(keys, values).get("id"));
                ids.add(id);
            }
            List<Player> players = getPlayersFromApi(ids);
            playersCSV = createPlayersCSV(players);
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return playersCSV;
    }

    private Map<String, String> getMap(String[] keys, String[] values) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    private List<Player> getPlayersFromApi(List<Long> ids) {
        try {
            // Set up the URL
            String urlString = generateApiUrl(ids);
            URL url = new URL(urlString);

            // Open the connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method
            connection.setRequestMethod("GET");

            // Set the Authorization header
            connection.setRequestProperty("Authorization", this.apiKey);

            // Get the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            PlayersDto players = gson.fromJson(response.toString(), PlayersDto.class);

            // Close the connection
            connection.disconnect();

            return players.getData();
        } catch (Exception e) {
            log.error("Error occurred while trying to get players from API", e);
        }
        return new ArrayList<>();
    }

    private File createPlayersCSV(List<Player> players) throws IOException {
        File file = File.createTempFile("players", ".csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "id", "first name", "last name" };
            writer.writeNext(header);

			for (Player player : players) {
                String[] row = new String[] {Integer.toString(player.getId()), player.getFirst_name(), player.getLast_name()};
                writer.writeNext(row);
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            log.error("Error occurred while trying to create players csv file", e);
        }
        return file;
    }

    public String generateApiUrl(List<Long> ids) {
        StringBuilder url = new StringBuilder(this.baseUrl + "?");
        for (Long id : ids) {
            url.append("player_ids[]=").append(id).append("&");
        }
        // Remove the last "&" character
        url.deleteCharAt(url.length() - 1);
        return url.toString();
    }
}
