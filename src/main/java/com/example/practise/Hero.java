package com.example.practise;

public class Hero extends BaseHero {
    public Hero() {
        super("/Images/HeroFinal.png");
        initialize();
    }

    private void initialize() {
        setFitHeight(74);
        setFitWidth(71);
        setLayoutX(5);
        setLayoutY(293);
    }

    // Specific Hero methods
}


