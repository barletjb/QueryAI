package com.myproject.queryai.springbootaicore.entity;

public class Prompt {

    private String text;
    private String theme;
    private int mois;


    public Prompt(String theme, int mois) {
        this.theme = theme;
        this.mois = mois;
        this.text = "Tu es un expert en " + theme + ". Explique-moi " + theme + " de manière claire, " +
                "structurée et progressive, en adaptant la profondeur " +
                "et les exemples au temps dont je dispose : " + mois + " mois.";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }
}
