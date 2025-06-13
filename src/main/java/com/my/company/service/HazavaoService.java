package com.my.company.service;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class HazavaoService {
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    public String getDefinition(String teny) throws Exception {
        String body = """
            {
              "model": "gpt-3.5-turbo",
              "messages": [
                {"role": "user", "content": "Hazavao amin'ny teny malagasy ny teny: %s"}
              ]
            }
            """.formatted(teny);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
