package com.coderscampus.Assignment10.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpoonacularConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Value("${spoonacular.urls.base}")
    private String baseUrl;

    @Value("${spoonacular.urls.mealplan}")
    private String mealplanEndpoint;

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getMealplanEndpoint() {
        return mealplanEndpoint;
    }
}
