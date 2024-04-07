package com.ocado.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocado.model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class JsonReaderImpl implements JsonReader {
    public Map<String, Product> readConfiguration(String path) {

        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
            String jsonContent = new String(inputStream.readAllBytes());

            Map<String, HashSet<String>> jsonMap = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });

            return jsonMap.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> new Product(entry.getKey(), entry.getValue())));

        } catch (IOException e) {
            Logger.getAnonymousLogger().info("Error reading config file: ");
            return new HashMap<>();
        }

    }

    public List<String> readBasket(String path) {

        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
            String jsonContent = new String(inputStream.readAllBytes());

            return objectMapper.readValue(jsonContent, new TypeReference<>() {
            });

        } catch (IOException e) {
            Logger.getAnonymousLogger().info("Error reading basket file: ");
            return new ArrayList<>();
        }
    }
}
