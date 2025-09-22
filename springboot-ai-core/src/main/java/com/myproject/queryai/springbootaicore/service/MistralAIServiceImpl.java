package com.myproject.queryai.springbootaicore.service;

import com.myproject.queryai.springbootaicore.entity.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MistralAIServiceImpl implements MistralAIService {

    private final String baseURL;
    private final String apiKey;
    private final String model;

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

        return prompt.getText();
    }
}
