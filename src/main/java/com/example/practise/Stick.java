package com.example.practise;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stick {
    private Rectangle stick;
    private double length;
    private boolean isGrowing;
    private AnchorPane gameLayout;

    public Stick(AnchorPane gameLayout) {
        this.gameLayout = gameLayout;
        this.stick = new Rectangle(3, 0, Color.BLACK); // Initialize with default width and no height
        this.length = 0;
        this.isGrowing = false;
    }

    public void createStick(double xCoordinate, double initialHeight) {
        this.stick.setLayoutX(xCoordinate);
        this.stick.setLayoutY(332 - initialHeight); // Adjust Y-coordinate based on initial height
        this.stick.setHeight(initialHeight);
        gameLayout.getChildren().add(stick);
    }

    public void grow() {
        if (isGrowing) {
            length++;
            stick.setLayoutY(stick.getLayoutY() - 1); // Move up as it grows
            stick.setHeight(stick.getHeight() + 1);
        }
    }

    public void stopGrowing() {
        this.isGrowing = false;
    }

    public void startGrowing() {
        this.isGrowing = true;
    }

    public double getLength() {
        return length;
    }

    public Rectangle getStick() {
        return stick;
    }

    // Add other Stick-specific methods as needed
}
