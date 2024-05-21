package com.example.practise;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Cherry {
    private ImageView cherry;
    private boolean cherryPresent;
    private double cherryPosition;

    public Cherry(AnchorPane gameLayout) {
        // Initialize the cherry ImageView once
        Image cherryImage = new Image(getClass().getResource("/com/example/oopsapply/Images/cherry.png").toExternalForm());
        this.cherry = new ImageView(cherryImage);
        this.cherry.setFitHeight(15);
        this.cherry.setFitWidth(15);
        gameLayout.getChildren().add(this.cherry);
    }

    public void createCherry(AnchorPane gameLayout, Pillar first, Pillar second) {
        Random random = new Random();
        boolean cherryYesOrNo = true; // For testing purposes, you can change this to random.nextBoolean()

        if (cherryYesOrNo && (second.getPillarObject().getLayoutX() - (first.getPillarObject().getLayoutX() + (first.getPillarObject().getWidth()))) > 40) {
            double cherryXCoordinate = calculateCherryPosition(first, second);
            this.cherry.setLayoutX(cherryXCoordinate);
            this.cherry.setLayoutY(330);

            // Update cherry presence and position variables
            cherryPresent = true;
            cherryPosition = cherryXCoordinate;
        }
    }

    private double calculateCherryPosition(Pillar first, Pillar second) {
        Random random = new Random();
        double lowerLimit = (first.getPillarObject().getWidth() + first.getPillarObject().getLayoutX()) + 15;
        double upperLimit = second.getPillarObject().getLayoutX() - 15;
        return random.nextDouble() * (upperLimit - lowerLimit) + lowerLimit;
    }

    public void removeCherry(AnchorPane gameLayout) {
        gameLayout.getChildren().remove(this.cherry);
        cherryPresent = false;
    }

    public boolean isCherryPresent() {
        return cherryPresent;
    }

    public double getCherryPosition() {
        return cherryPosition;
    }

    // Other methods or properties related to Cherry can be added here
}