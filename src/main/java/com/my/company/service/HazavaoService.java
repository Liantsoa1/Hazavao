package com.my.company.service;

import org.springframework.stereotype.Service;
import okhttp3.*;
import org.json.JSONObject;

@Service
public class HazavaoService {

    private final String apiKey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public HazavaoService() {
        apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("La variable d'environnement OPENAI_API_KEY n'est pas définie !");
        }
    }

    public String getDefinition(String teny) {
        try {
            OkHttpClient client = new OkHttpClient();

            String jsonBody = """
            {
              "model": "gpt-3.5-turbo",
              "messages": [
                {"role": "user", "content": "Hazavao amin'ny teny malagasy ny dikan'ny teny hoe: '%s'"}
              ]
            }
            """.formatted(teny);

            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    return "Tsy afaka nanazava ilay teny: " + teny;
                }
                String responseBody = response.body().string();
                JSONObject json = new JSONObject(responseBody);
                return json.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
            }
        } catch (Exception e) {
            return "Nisy olana: " + e.getMessage();
        }
    }
}

