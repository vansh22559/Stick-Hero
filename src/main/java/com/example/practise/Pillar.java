package com.example.practise;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Pillar {

    private Rectangle pillarObject;
    private static final int PILLAR_HEIGHT = 180;

    public Pillar(){

    }

    public Pillar(Rectangle pillarObject){
        this.pillarObject = pillarObject;
    }

    public Pillar createPillar(AnchorPane gameLayout, double xCoordinate){
        Rectangle rectangularPillar = new Rectangle(50, PILLAR_HEIGHT, Color.BLACK);
        rectangularPillar.setLayoutX(xCoordinate);
        rectangularPillar.setLayoutY(330.0);
        gameLayout.getChildren().add(rectangularPillar);
        this.pillarObject = rectangularPillar; // Assign the created pillar to the object property
        Pillar pillar1 = new Pillar(rectangularPillar);
        return pillar1;
    }

    public void createRandomPillar(AnchorPane gameLayout, Pillar first, Pillar second) {
        Random random = new Random();
        double lowerWidth = 10;
        double upperWidth = 200;
        double PILLAR_WIDTH = random.nextDouble() * (upperWidth - lowerWidth) + lowerWidth;

        Rectangle pillar = new Rectangle(PILLAR_WIDTH, PILLAR_HEIGHT, Color.BLACK);

        double lowerLimit = (first.getPillarObject().getWidth() + first.getPillarObject().getLayoutX() + 40);
        double upperLimit = gameLayout.getWidth() - 700;

        double randomXCoordinate = random.nextDouble() * (upperLimit - lowerLimit) + lowerLimit;

        pillar.setLayoutX(randomXCoordinate);
        pillar.setLayoutY(330.0);
        gameLayout.getChildren().add(pillar);

        second.setPillarObject(pillar);
        // Ensure you handle the 'second' pillar object appropriately based on your logic.
        // createCherry(gameLayout); // This method call is not defined here



        //ASSOCIATION SHOWN HERE
        Cherry cherry = new Cherry(gameLayout);
        cherry.createCherry(gameLayout,first,second);


    }

    // Getter and setter for the pillar object
    public Rectangle getPillarObject() {
        return this.pillarObject;
    }

    public void setPillarObject(Rectangle pillar) {
        this.pillarObject = pillar;
    }

    // You may need to implement other methods or adjust the code based on your requirements
}
