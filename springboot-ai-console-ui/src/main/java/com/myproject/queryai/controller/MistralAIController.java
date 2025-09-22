package com.myproject.queryai.controller;

import com.myproject.queryai.springbootaicore.service.MistralAIServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MistralAIController {

    private final MistralAIServiceImpl mistralAIService;

    public MistralAIController(MistralAIServiceImpl mistralAIService) {
        this.mistralAIService = mistralAIService;
    }

    public void generateRoadMapConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir votre thème ? ");
        String theme = scanner.nextLine();

        System.out.println("Veuillez saisir le nombre de mois ?");
        int mois = scanner.nextInt();

        String roadMap = mistralAIService.generateRoadMap(theme, mois);
        System.out.println(roadMap);
    }



}
