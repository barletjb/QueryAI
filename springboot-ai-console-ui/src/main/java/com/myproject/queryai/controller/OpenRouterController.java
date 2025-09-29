package com.myproject.queryai.controller;

import com.myproject.queryai.springbootaicore.service.OpenRouterServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class OpenRouterController {

    private final OpenRouterServiceImpl openRouterService;

    public OpenRouterController(OpenRouterServiceImpl mistralAIService) {
        this.openRouterService = mistralAIService;
    }

    public void generateRoadMapConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir votre thème ? ");
        String theme = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Veuillez saisir le nombre de mois ?");
        int mois = scanner.nextInt();

        String roadMap = openRouterService.generateRoadMap(theme, mois);
        System.out.println(roadMap);
    }


}
