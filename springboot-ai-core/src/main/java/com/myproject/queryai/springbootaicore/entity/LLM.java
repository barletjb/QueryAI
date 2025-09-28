package com.myproject.queryai.springbootaicore.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LLM {

    private final String baseURL;
    private final String apiKey;
    private final String model;

    // on va chercher avec value le .yaml
    public LLM(
            @Value("${openrouter.api.base-url}") String baseURL,
            @Value("${openrouter.api.key}") String apiKey,
            @Value("${openrouter.api.model}") String model
    ) {
        this.baseURL = baseURL;
        this.apiKey = apiKey;
        this.model = model;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getModel() {
        return model;
    }
}