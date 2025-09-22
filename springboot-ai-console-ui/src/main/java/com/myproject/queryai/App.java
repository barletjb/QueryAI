package com.myproject.queryai;


import com.myproject.queryai.controller.MistralAIController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {

    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        MistralAIController controller = context.getBean(MistralAIController.class);
        controller.generateRoadMapConsole();

    }

    @Value("${openrouter.api.key}")
    private String apiKey;

    @PostConstruct
    public void testKey() {
        System.out.println("Clé API : " + apiKey);
    }
}
