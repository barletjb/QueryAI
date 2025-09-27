package com.myproject.queryai.springbootaicore.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.myproject.queryai.springbootaicore.entity.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class MistralAIServiceImpl implements MistralAIService {

    private RestTemplate restTemplate =  new RestTemplate();
    private final String baseURL;
    private final String apiKey;
    private final String model;

    // on va chercher avec value le .yaml
    public MistralAIServiceImpl(@Value("${openrouter.api.base-url}") String baseURL,
                                @Value("${openrouter.api.key}") String apiKey,
                                @Value("${openrouter.api.model}") String model
    ) {
        this.baseURL = baseURL;
        this.apiKey = apiKey;
        this.model = model;
    }

    @Override
    public String generateRoadMap(String theme, int mois) {
        Prompt prompt = new Prompt(theme, mois);

        // Corps JSON simple représenté par des Map/List (Spring convertira en JSON)
        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "user", "content", prompt.getText())
                )
        );

        //Création du hearders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.add("HTTP-Referer", "http://localhost");
        headers.add("X-Title", "SpringBoot AI Demo");

        //Création de l'entity, avec la map body + headers
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        // On demande à RestTemplate de nous rendre un JsonNode
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(
                //Doc de openrouter https://openrouter.ai/docs/api-reference/chat-completion
                baseURL + "/chat/completions",
                request,
                JsonNode.class
        );

        JsonNode root = response.getBody();
        if (root == null) {
            return "Réponse vide de l'API";
        }

        // Navigation dans JSON
        //Idem Doc de openrouter https://openrouter.ai/docs/api-reference/chat-completion pour les Keys
        JsonNode contentNode = root.path("choices")   // .path ne jette pas d'exception si absent
                // pour JsonNode, .path(0) = premier élément
                .path(0)
                .path("message")
                .path("content");

        //AsText renvoie le string ou le message d'erreur
        return contentNode.asText("Réponse introuvable dans la réponse JSON");
    }
}
