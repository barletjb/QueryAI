package com.myproject.queryai.springbootaicore.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.myproject.queryai.springbootaicore.entity.LLM;
import com.myproject.queryai.springbootaicore.entity.Prompt;
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
    private final LLM mistral;


    public MistralAIServiceImpl(LLM mistral) {
        this.mistral = mistral;
    }

    @Override
    public String generateRoadMap(String theme, int mois) {
        Prompt prompt = new Prompt(theme, mois);

        // Corps JSON simple représenté par des Map/List (Spring convertira en JSON)
        Map<String, Object> body = Map.of(
                "model", mistral.getModel(),
                "messages", List.of(
                        Map.of("role", "user", "content", prompt.getText())
                )
        );

        //Création du hearders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(mistral.getApiKey());
        headers.add("HTTP-Referer", "http://localhost");
        headers.add("X-Title", "SpringBoot AI Demo");

        //Création de l'entity, avec la map body + headers
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        // On demande à RestTemplate de nous rendre un JsonNode
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(
                //Doc de openrouter https://openrouter.ai/docs/api-reference/chat-completion
                mistral.getBaseURL() + "/chat/completions",
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
