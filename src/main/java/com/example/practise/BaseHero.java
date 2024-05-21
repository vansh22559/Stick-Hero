package com.example.practise;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class BaseHero {
    protected ImageView imageView;

    public BaseHero(String imagePath) {
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        this.imageView = new ImageView(image);
    }

    public void setFitHeight(double height) {
        imageView.setFitHeight(height);
    }

    public void setFitWidth(double width) {
        imageView.setFitWidth(width);
    }

    public void setLayoutX(double x) {
        imageView.setLayoutX(x);
    }

    public void setLayoutY(double y) {
        imageView.setLayoutY(y);
    }

    public ImageView getImageView() {
        return imageView;
    }

    // Add other common methods as needed
}
