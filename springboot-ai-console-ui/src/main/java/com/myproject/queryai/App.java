package com.myproject.queryai;

import com.myproject.queryai.controller.MistralAIController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class App {

    public static void main( String[] args ) {

        //Je test d'abord dans la console
        SpringApplication app = new SpringApplication(App.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);


    }

    // CommandLineRunner est exécuté après le démarrage de Spring Boot
    @Bean
    CommandLineRunner run(MistralAIController controller) {
        return args -> {
            controller.generateRoadMapConsole();
        };
    }

}
