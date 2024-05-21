package com.example.practise;

import java.io.*;

import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class yoyo extends Application {
    private Timeline stickSoundLoop;
    private Label cherryCountLabel;
    private Label scoreLabel;
    private Label savedLabel;
    private List<Double> platformWidths = new ArrayList<>();
    private int score = 0;
    private Scene homescene;
    private Stage primaryStage;
    private static final int PILLAR_HEIGHT = 180;
    private Rectangle first, second;
    private double secondPillarWidth = 0.0;
    private double firstPillarWidth = 0.0;
    private double stickLength = 0.0;
    private boolean gameState = true;
    private Rectangle stick;
    private int highScore;

    private double initialHeight = 0; // Initial height of the stick
    private boolean isGrowing = false;  // Flag to check if the stick is currently growing
    private AnchorPane gameLayout;
    public Hero hero;
    public InvertedHero invertedHero;
    public ImageView cherry;
    private double cherryPosition = -1;
    private int cherryCount = 0 ;
    private boolean cherryPresent = false;
    private boolean upside = true,down = false;
    private boolean cherryCollected = false;
    private boolean notDied = true;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{
            MediaManager mediaManager = MediaManager.getInstance();
            MediaPlayer backgroundMusicPlayer = mediaManager.getBackgroundMusicPlayer();
            AudioClip stickGrowingSound = mediaManager.getStickGrowingSound();
            AudioClip gameOverSound = mediaManager.getGameOverSound();
            MediaPlayer mediaPlayer = mediaManager.getMediaPlayer();

            this.primaryStage = stage;
            AnchorPane homelayout = new AnchorPane();
            homescene = new Scene(homelayout, 1000, 600);
            Image backgroundImage = new Image(getClass().getResource("/Images/1.png").toExternalForm());
            ImageView backgroundImageView = new ImageView(backgroundImage);
            backgroundImageView.setFitWidth(1200);
            backgroundImageView.setFitHeight(600);

            notDied = true;
            cherryCollected = false;
            upside = true;
            down = false;

            Button btn = new Button();
            btn.setStyle("-fx-background-color: transparent;"); // Make the button transparent
            btn.setPrefSize(200, 50);  // Adjust these values

            Button btn2 = new Button();
            btn2.setStyle("-fx-background-color: transparent;"); // Make the button transparent
            btn2.setPrefSize(200, 50);
            // Use AnchorPane to set the position of the button
            AnchorPane.setTopAnchor(btn, 300.0);
            AnchorPane.setLeftAnchor(btn, 400.0);
            AnchorPane.setTopAnchor(btn2, 390.0);
            AnchorPane.setLeftAnchor(btn2, 410.0);

            // Set an event handler for the button
            btn.setOnAction(e -> {

                GameData data = deserializeGameData();
                highScore= data.getHighScore();
                score=0;
                cherryCount=0;
                stage.setScene(createGameScene(stage));
            });
            btn2.setOnAction(e -> {
                GameData data = deserializeGameData();
                score=data.getScore();
                cherryCount= data.getCherryCount();
                highScore= data.getHighScore();
                stage.setScene(createGameScene(stage));
            });


            // Create a MediaView to display the video
            if (mediaPlayer != null) {
                MediaView mediaView = new MediaView(mediaPlayer);
                mediaView.setFitWidth(1200);
                mediaView.setFitHeight(600);
                homelayout.getChildren().add(mediaView);

                mediaPlayer.setOnEndOfMedia(() -> {
                    homelayout.getChildren().add(backgroundImageView);
                    homelayout.getChildren().add(btn);
                    homelayout.getChildren().add(btn2);
                    mediaPlayer.stop();
                });

                mediaPlayer.play();
            }

            homelayout.setOnMouseClicked(e -> {
                stage.setScene(createGameScene(stage));
            });

            stage.setTitle("Stick Hero");
            stage.setScene(homescene);
            stage.show();

            stickSoundLoop = new Timeline(
                    new KeyFrame(Duration.millis(1), event -> {
                        stickGrowingSound.play();
                    })
            );
            stickSoundLoop.setCycleCount(Timeline.INDEFINITE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Scene createGameScene(Stage stage) {
        try{
            MediaManager mediaManager = MediaManager.getInstance();
            MediaPlayer backgroundMusicPlayer = mediaManager.getBackgroundMusicPlayer();
            gameState = true;
            notDied = true;
            cherryCollected = false;
            upside = true;
            down = false;
            cherryPresent = false;
            cherryPosition = -1;

            gameLayout = new AnchorPane();
            Scene gameScene = new Scene(gameLayout, 1000, 500);

            Image temp = new Image(getClass().getResource("/Images/4.png").toExternalForm());

            savedLabel = new Label("Progress Saved!");
            savedLabel.setStyle("-fx-font-size: 20; -fx-text-fill: #1b5150; -fx-font-weight: bold;");
            savedLabel.setLayoutX(50); // Adjust X coordinate as needed
            savedLabel.setLayoutY(45);
            scoreLabel = new Label("" + score);
            scoreLabel.setStyle("-fx-font-size: 50; -fx-text-fill: #1b5150; -fx-font-weight: bold;");
            scoreLabel.setLayoutX(470); // Adjust X coordinate as needed
            scoreLabel.setLayoutY(150);
            cherryCountLabel = new Label(" "+cherryCount);
            cherryCountLabel.setStyle("-fx-font-size: 40; -fx-text-fill: #1b5150; -fx-font-weight: bold;");
            cherryCountLabel.setLayoutX(950); // Adjust X coordinate as needed
            cherryCountLabel.setLayoutY(45);

            ImageView background = new ImageView(temp);
            background.setFitWidth(1000);//setting background image according to size of gameScene
            background.setFitHeight(600);
            if (backgroundMusicPlayer != null) {
                backgroundMusicPlayer.play();
            }

            hero = new Hero();
            invertedHero = new InvertedHero();

            gameLayout.getChildren().add(background);
            gameLayout.getChildren().add(scoreLabel);
            gameLayout.getChildren().add(cherryCountLabel);
            gameLayout.getChildren().add(hero.getImageView());
            gameLayout.getChildren().add(invertedHero.getImageView());

            hero.setFitHeight(74);
            hero.setFitWidth(71);
            hero.setLayoutX(5);
            hero.setLayoutY(293);

            invertedHero.setFitHeight(74);
            invertedHero.setFitWidth(71);
            invertedHero.setLayoutX(5);
            invertedHero.setLayoutY(293);

            invertedHero.imageView.setVisible(false);

            first = createPillar(gameLayout,0);
            second = createPillar(gameLayout,180);
            firstPillarWidth = 50;
            secondPillarWidth = 50;

            createCherry(gameLayout);

            if (gameState) {
                startGameLoop(gameScene);
            }

            return gameScene;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void createCherry(AnchorPane gameLayout){
        try{
            Random random = new Random();
            //now creating cherry
//        boolean cherryYesOrNo = random.nextBoolean();
            boolean cherryYesOrNo = true;
            if((cherryYesOrNo && (second.getLayoutX() - (first.getLayoutX() + first.getWidth())) > 40) ){
                Image temp3 = new Image(getClass().getResource("/Images/cherry.png").toExternalForm());
                cherry = new ImageView(temp3);
                cherry.setFitHeight(15);
                cherry.setFitWidth(15);
                double lowerLimitForCherry = (firstPillarWidth + first.getLayoutX()) + 15;
                double upperLimitForCherry = second.getLayoutX() - 15;

                // Position the cherry between the pillars
                double cherryXCoordinate = random.nextDouble(upperLimitForCherry - lowerLimitForCherry) + lowerLimitForCherry;
                cherry.setLayoutX(cherryXCoordinate);
                cherry.setLayoutY(330);
                gameLayout.getChildren().add(cherry);
                cherryPresent = true;
                cherryPosition = cherryXCoordinate;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    private void startGameLoop(Scene gameScene) {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameState) {
                    updateGameState(gameScene);
                } else {
                    stop();
                    System.out.println("Game Over!");
                }
            }
        };
        gameLoop.start();
    }

    private void updateGameState(Scene gameScene) {
        try{
            // Create a stick only if it's not growing
            if (!isGrowing) {
                // Handle mouse events
                gameScene.setOnMousePressed(this::handleMousePressed);
                gameScene.setOnMouseReleased(this::handleMouseReleased);

                if(hero.imageView.getLayoutX() != 5.0 ){
                    gameScene.setOnKeyPressed(this::handleKeyPress);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private Rectangle createPillar(AnchorPane gamelayout, double xCoordinate) {
        double PILLAR_WIDTH = 50;
        Rectangle pillar = new Rectangle(PILLAR_WIDTH, PILLAR_HEIGHT, Color.BLACK);
        pillar.setLayoutX(xCoordinate);
        pillar.setLayoutY(330.0);
        gamelayout.getChildren().add(pillar);
        return pillar;
    }

    private Rectangle createStick(double xCoordinate) {
        Rectangle rectangle = new Rectangle(xCoordinate - 1, 332, 3, initialHeight);
        rectangle.setFill(Color.BLACK);
        return rectangle;
    }

    private void handleKeyPress(javafx.scene.input.KeyEvent event){
        if (event.getCode() == KeyCode.SPACE) {
            if(upside){
                //hero is in upward position , so flip it
                hero.imageView.setVisible(false);
                invertedHero.imageView.setVisible(true);
                upside = false;
                down = true;
            }
            else{
                hero.imageView.setVisible(true);
                invertedHero.imageView.setVisible(false);
                upside = true;
                down = false;
            }
        }
        if(event.getCode() == KeyCode.ENTER){
            //tera logic like terko jo bhi karna hai save karne ke lie 
            GameData data = new GameData(score,cherryCount,highScore);
            serializeGameData(data);
            displayMessage(gameLayout,"Progress Saved!");
        }
    }

    private void handleMousePressed(MouseEvent event) {
        MediaManager mediaManager = MediaManager.getInstance();
        MediaPlayer backgroundMusicPlayer = mediaManager.getBackgroundMusicPlayer();
        AudioClip stickGrowingSound = mediaManager.getStickGrowingSound();

        isGrowing = true;
        stick = createStick(first.getLayoutX() + firstPillarWidth);
        gameLayout.getChildren().add(stick);
        if (stickGrowingSound != null) {
            stickSoundLoop.play();
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleStick();
                if (!isGrowing) {
                    stopGrowing(); // Function to stop the stick growth when mouse released
                    this.stop();
                }
            }
        }.start();
    }

    private void stopGrowing() {
        stickSoundLoop.stop();
        isGrowing = false;
    }


    private void handleMouseReleased(MouseEvent event) {
        MediaManager mediaManager = MediaManager.getInstance();
        MediaPlayer backgroundMusic = mediaManager.getBackgroundMusicPlayer();
        stickSoundLoop.stop();
//        if (backgroundMusic != null) {
//            backgroundMusic.play();
//        }
        isGrowing = false;

        stickLength = stick.getHeight();

        // Set the pivot point to the bottom center of the stick
        double pivotX = stick.getX();
        double pivotY = stick.getY() + stick.getHeight();

        stick.getTransforms().clear();  // Clear any existing transformations

        // Create a Rotate object
        Rotate rotate = new Rotate(0, pivotX, pivotY);

        stick.getTransforms().add(rotate);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new KeyValue(rotate.angleProperty(), 90)));
        timeline.play();

        //now move hero to end of the stick
        timeline.setOnFinished(event3 -> {
            moveHero();
        });
    }


    private void moveHero() {
        try{
            double distance;
            boolean flag;
            double differenceBetweenBothPillars = second.getLayoutX() - (first.getLayoutX() + firstPillarWidth) ;
            if ((stickLength + 1 < differenceBetweenBothPillars) || (stickLength > secondPillarWidth + differenceBetweenBothPillars)){
                //when player loses
                flag = false;
                distance = stickLength;
            } else {
                distance = (secondPillarWidth + second.getLayoutX()) - (first.getLayoutX() + firstPillarWidth);
                flag = true;
            }


            if(!flag){
                //simply move it till end of stick ..
                Timeline deathTimeline = new Timeline();

                KeyFrame keyFrame7 = new KeyFrame(Duration.seconds(2),
                        new KeyValue(hero.imageView.layoutXProperty(), hero.imageView.getLayoutX() + stickLength));

                KeyFrame keyFrame8 = new KeyFrame(Duration.seconds(2),
                        new KeyValue(invertedHero.imageView.layoutXProperty(), invertedHero.imageView.getLayoutX() + stickLength));

                //handle cherry case later
                deathTimeline.getKeyFrames().addAll(keyFrame7,keyFrame8);

                deathTimeline.play();

                deathTimeline.setOnFinished(event -> {
                    gameState = false;
                    fallHero();
                });
            }

            //else hero moves to other pillar

            Timeline heroTimeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),
                    new KeyValue(hero.imageView.layoutXProperty(), hero.imageView.getLayoutX() + distance ));

            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(2),
                    new KeyValue(invertedHero.imageView.layoutXProperty(), invertedHero.imageView.getLayoutX() + distance));

            // Add the KeyFrame to the Timeline
            heroTimeline.getKeyFrames().addAll(keyFrame, keyFrame1);

            heroTimeline.play();

            final Timeline[] checkInvertedAndCherry = {null}; // Declare a final array to hold the timeline reference


            // Set up a periodic checking event in the timeline
            checkInvertedAndCherry[0] = new Timeline(new KeyFrame(Duration.millis(10), event -> {
                // Check if the hero is in inverted position and cherry is present at its X-coordinate

                double newXcoordinateInvertedHero = invertedHero.imageView.getLayoutX() + 35;
                if (down && cherryPresent && !cherryCollected && ((newXcoordinateInvertedHero >= cherryPosition) && (newXcoordinateInvertedHero - 20 <= cherryPosition ) )) {
                    cherryCount += 1;
                    cherryCountLabel.setText(" " + cherryCount);
                    cherry.setVisible(false);
                    cherryCollected = true;
                    MediaManager.getInstance().getCollectCherrySound().play();
                }

                newXcoordinateInvertedHero = invertedHero.imageView.getLayoutX() + 44;
                if(down && notDied &&  ((newXcoordinateInvertedHero >= second.getLayoutX()))){
                    death();
                    notDied = false;
                    gameState = false;
                    heroTimeline.stop();
                }
            }));
            checkInvertedAndCherry[0].setCycleCount(Timeline.INDEFINITE);
            checkInvertedAndCherry[0].play();


            heroTimeline.setOnFinished(event5->{
                // Hero has successfully crossed pillars, now shift the game pane
                if(gameState){
                    shiftGamePane();
                    first = second;
                    firstPillarWidth = secondPillarWidth;
                    cherryCollected = false;
                    score ++;
                    scoreLabel.setText("" + score);
                    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                    pauseTransition.setOnFinished(pauseEvent -> createRandomPillar(gameLayout));
                    pauseTransition.play();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void death(){
        try{
            MediaManager mediaManager = MediaManager.getInstance();
            MediaPlayer backgroundMusicPlayer = mediaManager.getBackgroundMusicPlayer();
            AudioClip gameOverSound = mediaManager.getGameOverSound();
            backgroundMusicPlayer.stop();
            gameOverSound.play();

            Timeline HerofallingTimeline = new Timeline();

            // Add a KeyFrame to the Timeline
            KeyFrame keyFramex = new KeyFrame(Duration.seconds(2),
                    new KeyValue(hero.imageView.layoutYProperty(), hero.imageView.getLayoutY() + 500));

            KeyFrame keyFramex2 = new KeyFrame(Duration.seconds(2),
                    new KeyValue(invertedHero.imageView.layoutYProperty(), invertedHero.imageView.getLayoutY() + 500));

            HerofallingTimeline.getKeyFrames().addAll(keyFramex,keyFramex2);

            HerofallingTimeline.setOnFinished(event -> {
                Scene gameOverScene = createGameOverScene(primaryStage,score,cherryCount,highScore);
                primaryStage.setScene(gameOverScene);
            });

            HerofallingTimeline.play();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    private Scene createGameOverScene(Stage stage, int score, int cherryCount,int highScore) {
        try{
            AnchorPane gameOverLayout = new AnchorPane();
            Scene gameOverScene = new Scene(gameOverLayout, 1000, 600);

            // Load the background image
            Image temp = new Image(getClass().getResource("/Images/3.png").toExternalForm());
            ImageView background = new ImageView(temp);
            background.setFitWidth(1000);
            background.setFitHeight(600);

            // Add the background image to the layout
            gameOverLayout.getChildren().add(background);

            // Display the score using a Label
            Label cherryLabel = new Label("" + cherryCount);
            Label scoreLabel = new Label("" + score);
            Label highscoreLabel = new Label("" + highScore);
            cherryLabel.setStyle("-fx-font-size: 40; -fx-text-fill: #1b5150; -fx-font-weight: bold;");
            scoreLabel.setStyle("-fx-font-size: 40; -fx-text-fill: #1b5150; -fx-font-weight: bold;");
            highscoreLabel.setStyle("-fx-font-size: 40; -fx-text-fill: #1b5150; -fx-font-weight: bold;");

            AnchorPane.setTopAnchor(cherryLabel, 285.0);
            AnchorPane.setLeftAnchor(cherryLabel, 600.0);
            gameOverLayout.getChildren().add(cherryLabel);

            AnchorPane.setTopAnchor(scoreLabel, 230.0);
            AnchorPane.setLeftAnchor(scoreLabel, 600.0);
            AnchorPane.setRightAnchor(scoreLabel, 150.0); // Center the label horizontally
            //AnchorPane.setBottomAnchor(scoreLabel, 300.0); // Center the label vertically
            AnchorPane.setTopAnchor(highscoreLabel, 370.0);
            AnchorPane.setLeftAnchor(highscoreLabel, 600.0);
            AnchorPane.setRightAnchor(highscoreLabel, 150.0); // Center the label horizontally

            gameOverLayout.getChildren().add(highscoreLabel);
            gameOverLayout.getChildren().add(scoreLabel);

            // Example: Add a circular "Play Again" button at the bottom right corner
            Circle playAgainButton = new Circle(25, Color.TRANSPARENT); // Adjust the radius and color as needed
            playAgainButton.setOnMouseClicked(e -> {

                // Reset game state or perform any necessary actions to restart the game
                this.score = 0;
                this.cherryCount = 0;

                stage.setScene(createGameScene(stage));
            });

            // Set the position of the circular button to the bottom right corner
            AnchorPane.setBottomAnchor(playAgainButton, 30.0);
            AnchorPane.setRightAnchor(playAgainButton, 20.0);

            // Add the circular button to the layout
            gameOverLayout.getChildren().add(playAgainButton);

            // Example: Add another circular "Home" button at the bottom left corner
            Circle homeButton = new Circle(25, Color.TRANSPARENT); // Adjust the radius and color as needed
            homeButton.setOnMouseClicked(e -> {
                GameData data = deserializeGameData();
                this.score = data.getScore();
                this.cherryCount = data.getCherryCount();
                // Go to the home scene
                stage.setScene(homescene);
            });

            // Set the position of the home button to the bottom left corner
            AnchorPane.setBottomAnchor(homeButton, 30.0);
            AnchorPane.setLeftAnchor(homeButton, 20.0);

            // Add the home button to the layout
            gameOverLayout.getChildren().add(homeButton);

            ///////////////////////////////////////////////////////////////////
            //        adding revive button


            // Create a new circular button at the midpoint
            Circle newButton = new Circle(50, Color.TRANSPARENT); // Adjust the radius and color as needed
            newButton.setOnMouseClicked(e -> {
                // Add actions for the new button here
                // For example, to perform some action when this button is clicked
                if(cherryCount >= 2){
                    // Reset game state or perform any necessary actions to restart the game
                    this.cherryCount -= 2;

                    stage.setScene(createGameScene(stage));
                }
                else{
                    displayMessage(gameOverLayout, "Not enough cherries!");

                }
            });

            // Set the position of the new button at the calculated midpoint
            AnchorPane.setBottomAnchor(newButton, 15.0); // Adjust the positioning as needed
            AnchorPane.setLeftAnchor(newButton, 420.0); // Adjust the positioning as needed

            // Add the new button to the layout
            gameOverLayout.getChildren().add(newButton);

            return gameOverScene;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void displayMessage(AnchorPane pane, String message) {
        try{
            Label notification = new Label(message);
            notification.setStyle("-fx-background-color: #CDEAC0; -fx-text-fill: #025928; -fx-padding: 26px; -fx-font-size: 18px;-fx-alignment: center;");
            notification.setMinWidth(300); // Adjust the width of the notification box
            notification.setMinHeight(150); // Adjust the height of the notification box
            notification.setLayoutX((pane.getWidth() - notification.getMinWidth()) / 2);
            notification.setLayoutY((pane.getHeight() - notification.getMinHeight()) / 2);
            pane.getChildren().add(notification);

            // Fade out the notification after 2 seconds
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), notification);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> pane.getChildren().remove(notification));
            fadeOut.play();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private void fallHero() {
        try{
            death();

            MediaManager mediaManager = MediaManager.getInstance();
            MediaPlayer backgroundMusicPlayer = mediaManager.getBackgroundMusicPlayer();
            AudioClip gameOverSound = mediaManager.getGameOverSound();
            backgroundMusicPlayer.stop();
            gameOverSound.play();

            GameData data = new GameData(score, cherryCount,highScore);

            //now written code to make the stick fall down
            double pivotX = stick.getX() + stick.getWidth() / 2;
            double pivotY = stick.getY() + stick.getHeight();

            // Create a Rotate object
            Rotate rotate = new Rotate(0, pivotX, pivotY);

            // Apply the initial rotation to the ImageView
            stick.getTransforms().add(rotate);

            // Create a final Timeline array with a single element
            final Timeline[] timeline = new Timeline[1];

            // Create a KeyFrame for the falling animation
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.01), e -> {
                if (rotate.getAngle() < 90) {
                    rotate.setAngle(rotate.getAngle() + 2); // Adjust the increment as needed
                } else {
                    timeline[0].stop();
                }
            });

            // Create a Timeline for the falling animation
            timeline[0] = new Timeline(keyFrame);
            timeline[0].setCycleCount(Timeline.INDEFINITE);
            timeline[0].play();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void shiftGamePane(){
        try{
            //MAKING TIMELINE FOR MOVING OBJECTS
            double DistanceToMove = second.getLayoutX() - first.getLayoutX();

            Timeline timeline = new Timeline();

            // Add a KeyFrame to the Timeline
            KeyFrame keyFrame6 = null;
            if(cherry != null){
                keyFrame6 = new KeyFrame(Duration.seconds(1)
                        ,
                        new KeyValue(cherry.layoutXProperty(), cherry.getLayoutX() - DistanceToMove));
            }

            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(1),
                    new KeyValue(stick.layoutXProperty(), stick.getLayoutX() -DistanceToMove));

            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1),
                    new KeyValue(hero.imageView.layoutXProperty(), hero.imageView.getLayoutX() - DistanceToMove));

            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1),
                    new KeyValue(first.layoutXProperty(), first.getLayoutX() - DistanceToMove));

            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(1),
                    new KeyValue(second.layoutXProperty(), second.getLayoutX() - DistanceToMove));

            KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(1),
                    new KeyValue(invertedHero.imageView.layoutXProperty(), invertedHero.imageView.getLayoutX() - DistanceToMove));

            // Add the KeyFrame to the Timeline
            if(cherry == null)
            {
                timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5);
            }
            else{
                timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6);
            }

            timeline.play();

            timeline.setOnFinished(event -> {
                stick.setVisible(false);
                cherryPosition = -1;
                cherryPresent = false;
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    private void handleStick() {
        if (isGrowing && stick != null) {
            // Calculate the increment in stick's Y position
            double incrementY = 4;

            // Adjust the stick's Y position and decrease its height
            stick.setLayoutY(stick.getLayoutY() - incrementY);
            stick.setHeight(stick.getHeight() + incrementY);
        }
    }
    void createRandomPillar(AnchorPane gameLayout) {
        try{
            Random random = new Random();
            double lowerWidth = 10;
            double upperWidth = 200;
            double PILLAR_WIDTH = random.nextDouble(upperWidth - lowerWidth) + lowerWidth;

            Rectangle pillar = new Rectangle(PILLAR_WIDTH, PILLAR_HEIGHT, Color.BLACK);
            double lowerLimit = (firstPillarWidth+first.getLayoutX()+40);
            double upperLimit = gameLayout.getWidth()-700;

            double randomXCoordinate = random.nextDouble(upperLimit - lowerLimit) + lowerLimit;

            pillar.setLayoutX(randomXCoordinate);
            pillar.setLayoutY(330.0);
            gameLayout.getChildren().add(pillar);

            second = pillar;
            secondPillarWidth = PILLAR_WIDTH;

            createCherry(gameLayout);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void iterateOverPillarWidths() {
        Iterator<Double> iter = platformWidths.iterator();
        try {
            while (iter.hasNext()) {
                Double width = iter.next();
            }
        } catch (Exception e) {
            // Handle any exceptions here
        }
    }

    public int getScore() {
        return score;
    }

    public AnchorPane getGameLayout() {
        return gameLayout;

    }

    public Rectangle getFirstPillar() {
        return first;
    }

    public Rectangle getSecondPillar() {
        return second;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    public int getCherryCount() {
        return cherryCount;
    }

    void serializeGameData(GameData data) {
        try (FileOutputStream fileOut = new FileOutputStream("gameData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    GameData deserializeGameData() {
        GameData data = null;
        try (FileInputStream fileIn = new FileInputStream("gameData.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            data = (GameData) in.readObject();

        } catch (IOException i) {
            i.printStackTrace();
            return new GameData(0, 0, 0); // If failed, return default data
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return new GameData(0, 0, 0); // If failed, return default data
        }
        return data;
    }

    private int getHighScore() {
        GameData data = deserializeGameData();
        return data.getHighScore();
    }

    private void updateHighScore() {
        GameData data = deserializeGameData();
        if (score > data.getHighScore()) {
            data.setHighScore(score);
            serializeGameData(data);
        }
    }
}
