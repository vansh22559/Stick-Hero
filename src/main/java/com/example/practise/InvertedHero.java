package com.example.practise;


public class InvertedHero extends BaseHero {
    public InvertedHero() {
        super("/Images/HeroInverted.png");
        initialize();
    }

    private void initialize() {
        setFitHeight(74);
        setFitWidth(71);
        setLayoutX(5);
        setLayoutY(293);
    }

    // Specific InvertedHero methods
}
