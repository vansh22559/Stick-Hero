////This code works perfectly but in this cherries are not there.
//
//package com.example.practise;
//
//import javafx.animation.*;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.Pane;
//import javafx.scene.media.AudioClip;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.transform.Rotate;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.util.Random;
//
//public class yoyo_Version2 extends Application {
//    private int score = 0;
//    private Scene homescene;
//    private Stage primaryStage;
//    private static final int PILLAR_HEIGHT = 180;
//    private Rectangle first,second;
//    private double secondPillarWidth = 0.0;
//    private double firstPillarWidth = 0.0;
//    private double stickLength = 0.0;
//    private boolean gameState = true;
//    private Rectangle stick;
//    private double initialHeight = 0; // Initial height of the stick
//    private boolean isGrowing = false;  // Flag to check if the stick is currently growing
//    private AnchorPane gameLayout;
//    private Hero hero;
//    private InvertedHero invertedHero;
//    private ImageView cherry;
//    private double cherryPosition = -1;
//    private int cherryCount = 0 ;
//    private boolean cherryPresent = false;
//    private boolean upside = true,down = false;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        this.primaryStage = stage;
//        AnchorPane homelayout = new AnchorPane();
//        homescene = new Scene(homelayout, 1200, 600);
//
//        Image backgroundImage = new Image(getClass().getResource("/Images/Stick Hero.png").toExternalForm());
//        ImageView backgroundImageView = new ImageView(backgroundImage);
//        backgroundImageView.setFitWidth(1200);
//        backgroundImageView.setFitHeight(600);
//
//        Button btn = new Button();
//        btn.setStyle("-fx-background-color: transparent;"); // Make the button transparent
//        btn.setPrefSize(1200, 600);  // Adjust these values
//
//        // Use AnchorPane to set the position of the button
//        AnchorPane.setTopAnchor(btn, 0.0);
//        AnchorPane.setLeftAnchor(btn, 0.0);
//
//        // Set an event handler for the button
//        btn.setOnAction(e -> {
//            stage.setScene(createGameScene(stage));
//        });
//
//        homelayout.getChildren().add(backgroundImageView);
//        homelayout.getChildren().add(btn);
//
//        // Set an event handler for the homelayout to trigger the scene transition
//        homelayout.setOnMouseClicked(e -> {
//            stage.setScene(createGameScene(stage));
//        });
//
//        stage.setTitle("Stick Hero");
//        stage.setScene(homescene);
//        stage.show();
//    }
//    public Scene createGameScene(Stage stage) {
//        gameState = true;
//        gameLayout = new AnchorPane();
//        Scene gameScene = new Scene(gameLayout, 1200, 500);
//
//        Image temp = new Image(getClass().getResource("/Images/BackgroundNew1.png").toExternalForm());
//
//        ImageView background = new ImageView(temp);
//        background.setFitWidth(1200);//setting background image according to size of gameScene
//        background.setFitHeight(600);
//
//        hero = new Hero();
//        invertedHero = new InvertedHero();
//
//        gameLayout.getChildren().add(background);
//
//        gameLayout.getChildren().add(hero.getImageView());
//        gameLayout.getChildren().add(invertedHero.getImageView());
//
//        hero.setFitHeight(74);
//        hero.setFitWidth(71);
//        hero.setLayoutX(5);
//        hero.setLayoutY(293);
//
//
//        invertedHero.setFitHeight(74);
//        invertedHero.setFitWidth(71);
//        invertedHero.setLayoutX(5);
//        invertedHero.setLayoutY(293);
//
//        invertedHero.imageView.setVisible(false);
//
//        first = createPillar(gameLayout,0);
//        second = createPillar(gameLayout,180);
//        firstPillarWidth = 50;
//        secondPillarWidth = 50;
//
//        createCherry(gameLayout);
//
//        if(gameState){
//            startGameLoop(gameScene);
//        }
//
//        System.out.println("finished");
//        return gameScene;
//    }
//
//    private void startGameLoop(Scene gameScene) {
//        AnimationTimer gameLoop = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                if (gameState) {
//                    updateGameState(gameScene);
//                } else {
//                    stop();
//                    System.out.println("Game Over!");
//                }
//            }
//        };
//        gameLoop.start();
//    }
//
//    private void updateGameState(Scene gameScene) {
//        // Create a stick only if it's not growing
//        if (!isGrowing) {
//            // Handle mouse events
//            gameScene.setOnMousePressed(this::handleMousePressed);
//            gameScene.setOnMouseReleased(this::handleMouseReleased);
//        }
//    }
//
//    private Rectangle createPillar(AnchorPane gamelayout,double xCoordinate){
//        double PILLAR_WIDTH = 50;
//        Rectangle pillar = new Rectangle(PILLAR_WIDTH, PILLAR_HEIGHT, Color.BLACK);
//        pillar.setLayoutX(xCoordinate);
//        pillar.setLayoutY(330.0);
//        gamelayout.getChildren().add(pillar);
//        return pillar;
//    }
//    private Rectangle createStick(double xCoordinate) {
//        Rectangle rectangle = new Rectangle(xCoordinate-1, 332, 3, initialHeight);
//        rectangle.setFill(Color.BLACK);
//        return rectangle;
//    }
//
////    private void handleKeyPress(javafx.scene.input.KeyEvent event){
////        if (event.getCode() == KeyCode.SPACE) {
////            if(upside){
////                //hero is in upward position , so flip it
////                hero.imageView.setVisible(false);
////                invertedHero.imageView.setVisible(false);
////                upside = false;
////                down = true;
////            }
////            else{
////                hero.imageView.setVisible(true);
////                invertedHero.imageView.setVisible(false);
////                upside = true;
////                down = false;
////            }
////        }
////    }
//
//    private void handleMousePressed(MouseEvent event) {
//        isGrowing = true;
//        stick = createStick(first.getLayoutX() + firstPillarWidth);
//        gameLayout.getChildren().add(stick);
//
//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                handleStick();
//                if (!isGrowing) {
//                    stopGrowing(); // Function to stop the stick growth when mouse released
//                    this.stop();
//                }
//            }
//        }.start();
//    }
//
//    private void stopGrowing() {
//        isGrowing = false;
//    }
//
//    private void handleMouseReleased(MouseEvent event) {
//        isGrowing = false;
//
//        stickLength = stick.getHeight();
//
//        // Set the pivot point to the bottom center of the stick
//        double pivotX = stick.getX();
//        double pivotY = stick.getY() + stick.getHeight();
//
//        stick.getTransforms().clear();  // Clear any existing transformations
//
//        // Create a Rotate object
//        Rotate rotate = new Rotate(0, pivotX, pivotY);
//
//        stick.getTransforms().add(rotate);
//
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5),new KeyValue(rotate.angleProperty(),90)));
//        timeline.play();
//
//        //now move hero to end of the stick
//        timeline.setOnFinished(event3 -> {
//            moveHero();
//        });
//    }
//    private void moveHero() {
//        double distance;
//        boolean flag;
//        double differenceBetweenBothPillars = second.getLayoutX() - (first.getLayoutX() + firstPillarWidth) ;
//        if ((stickLength + 1 < differenceBetweenBothPillars) || (stickLength > secondPillarWidth + differenceBetweenBothPillars)){
//            //when player loses
//            flag = false;
//            distance = stickLength;
//        } else {
//            distance = (secondPillarWidth + second.getLayoutX()) - (first.getLayoutX() + firstPillarWidth);
//            flag = true;
//            score ++;
//        }
//
//        // Create a Timeline for the hero movement
//        Timeline heroTimeline = new Timeline();
//
//        if(!flag){
//            //simply move it till end of stick ..
//            Timeline deathTimeline = new Timeline();
//
//            KeyFrame keyFrame7 = new KeyFrame(Duration.seconds(1),
//                    new KeyValue(hero.imageView.layoutXProperty(), hero.imageView.getLayoutX() + stickLength));
//
//            KeyFrame keyFrame8 = new KeyFrame(Duration.seconds(1),
//                    new KeyValue(invertedHero.imageView.layoutXProperty(), invertedHero.imageView.getLayoutX() + stickLength));
//
//            //handle cherry case later
//            deathTimeline.getKeyFrames().addAll(keyFrame7,keyFrame8);
//
//            deathTimeline.play();
//
//            deathTimeline.setOnFinished(event -> {
//                gameState = false;
//                fallHero();
//            });
//        }
//
//        //now check if there is cherry between the pillars
//        else if(!cherryPresent){
//            // Add a KeyFrame to the Timeline
//            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
//                    new KeyValue(hero.imageView.layoutXProperty(), hero.imageView.getLayoutX() + distance - secondPillarWidth - 45));//this sends the user to just the edge of pillar 2
//
//            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1),
//                    new KeyValue(invertedHero.imageView.layoutXProperty(),invertedHero.imageView.getLayoutX() + distance - secondPillarWidth - 45));
//
//            // Add the KeyFrame to the Timeline
//            heroTimeline.getKeyFrames().addAll(keyFrame,keyFrame1);
//
//            heroTimeline.play();
//
//            // Set an event handler for when the Timeline finishes
//            heroTimeline.setOnFinished(event -> {
//                if(down){
//                    //means that hero is in downward position and has collided with the pillar 2
//                    death();
//                    gameState = false;
//                }
//                else{
//                    //means crossed succesfully
//                    moveHeroToCornerOfPillar();
//                }
//            });
//        }
//
//        else{
//            //have to then break this 3 times
//
//            //first letting it go till cherry
//            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
//                    new KeyValue(hero.imageView.layoutXProperty(), hero.imageView.getLayoutX() + cherryPosition-45));
//
//            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1),
//                    new KeyValue(invertedHero.imageView.layoutXProperty(),invertedHero.imageView.getLayoutX() + cherryPosition-45));
//
//            // Add the KeyFrame to the Timeline
//            heroTimeline.getKeyFrames().addAll(keyFrame,keyFrame1);
//
//            heroTimeline.play();
//
//            heroTimeline.setOnFinished(event ->{
//                if(down){
//                    //make the cherry disappear
//                    ///////////////////////////
//                    cherryCount += 1;
//                    cherry.setVisible(false);
//                }
//
//                //remaining work is same for both cases, now send hero till end of the pillar
//
//                //create new timeline now
//
//                Timeline timelineEndMove = new Timeline();
//
//                KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(1),
//                        new KeyValue(hero.imageView.layoutXProperty(),(hero.imageView.getLayoutX() + (second.getLayoutX() - hero.imageView.getLayoutX())) - 45));
//
//                KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(1),
//                        new KeyValue(invertedHero.imageView.layoutXProperty(),(invertedHero.imageView.getLayoutX() + (second.getLayoutX() - invertedHero.imageView.getLayoutX())) - 45));
//
//                // Add the KeyFrame to the Timeline
//                timelineEndMove.getKeyFrames().addAll(keyFrame4,keyFrame5);
//
//                timelineEndMove.play();
//
//                timelineEndMove.setOnFinished(event5 -> {
//                    if(down){
//                        //means that hero is in downward position and has collided with the pillar 2
//                        death();
//                        // Game finished
//                        gameState = false;
//                    }
//                    else{
//                        //means crossed succesfully
//                        moveHeroToCornerOfPillar();
//                    }
//
//                });
//            });
//        }
//    }
//
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public Scene getHomescene() {
//        return homescene;
//    }
//
//    public void setHomescene(Scene homescene) {
//        this.homescene = homescene;
//    }
//
//    public Stage getPrimaryStage() {
//        return primaryStage;
//    }
//
//    public void setPrimaryStage(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//    }
//
//    public Rectangle getFirst() {
//        return first;
//    }
//
//    public void setFirst(Rectangle first) {
//        this.first = first;
//    }
//
//    public Rectangle getSecond() {
//        return second;
//    }
//
//    public void setSecond(Rectangle second) {
//        this.second = second;
//    }
//
//    public double getSecondPillarWidth() {
//        return secondPillarWidth;
//    }
//
//    public void setSecondPillarWidth(double secondPillarWidth) {
//        this.secondPillarWidth = secondPillarWidth;
//    }
//
//    public double getFirstPillarWidth() {
//        return firstPillarWidth;
//    }
//
//    public void setFirstPillarWidth(double firstPillarWidth) {
//        this.firstPillarWidth = firstPillarWidth;
//    }
//
//    public double getStickLength() {
//        return stickLength;
//    }
//
//    public void setStickLength(double stickLength) {
//        this.stickLength = stickLength;
//    }
//
//    public boolean isGameState() {
//        return gameState;
//    }
//
//    public void setGameState(boolean gameState) {
//        this.gameState = gameState;
//    }
//
//    public Rectangle getStick() {
//        return stick;
//    }
//
//    public void setStick(Rectangle stick) {
//        this.stick = stick;
//    }
//
//    public double getInitialHeight() {
//        return initialHeight;
//    }
//
//    public void setInitialHeight(double initialHeight) {
//        this.initialHeight = initialHeight;
//    }
//
//    public boolean isGrowing() {
//        return isGrowing;
//    }
//
//    public void setGrowing(boolean growing) {
//        isGrowing = growing;
//    }
//
//    public AnchorPane getGameLayout() {
//        return gameLayout;
//    }
//
//    public void setGameLayout(AnchorPane gameLayout) {
//        this.gameLayout = gameLayout;
//    }
//
//    public ImageView getCherry() {
//        return cherry;
//    }
//
//    public void setCherry(ImageView cherry) {
//        this.cherry = cherry;
//    }
//
//    public double getCherryPosition() {
//        return cherryPosition;
//    }
//
//    public void setCherryPosition(double cherryPosition) {
//        this.cherryPosition = cherryPosition;
//    }
//
//    public int getCherryCount() {
//        return cherryCount;
//    }
//
//    public void setCherryCount(int cherryCount) {
//        this.cherryCount = cherryCount;
//    }
//
//    public boolean isCherryPresent() {
//        return cherryPresent;
//    }
//
//    public void setCherryPresent(boolean cherryPresent) {
//        this.cherryPresent = cherryPresent;
//    }
//
//    public boolean isUpside() {
//        return upside;
//    }
//
//    public void setUpside(boolean upside) {
//        this.upside = upside;
//    }
//
//    public boolean isDown() {
//        return down;
//    }
//
//    public void setDown(boolean down) {
//        this.down = down;
//    }
//
//    private void moveHeroToCornerOfPillar(){
//        Timeline timeline1 = new Timeline();
//        //now create another timeline to move remaining distance
//        // Add a KeyFrame to the Timeline
//        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1),
//                new KeyValue(hero.imageView.layoutXProperty(), hero.imageView.getLayoutX() + secondPillarWidth));//this sends the user to just the edge of pillar 2
//
//        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(1),
//                new KeyValue(invertedHero.imageView.layoutXProperty(),invertedHero.imageView.getLayoutX() + secondPillarWidth));
//
//        // Add the KeyFrame to the Timeline
//        timeline1.getKeyFrames().addAll(keyFrame3,keyFrame4);
//
//        timeline1.play();
//
//        timeline1.setOnFinished(event4 -> {
//            // Hero has successfully crossed pillars, now shift the game pane\
//            System.out.println("first x coo "+first.getLayoutX() + " and second : " +second.getLayoutX());
//
//            shiftGamePane();
//
//            first = second;
//            firstPillarWidth = secondPillarWidth;
//
//
//            // Create a PauseTransition to delay the creation of the new pillar by 2 second
//            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
//            pauseTransition.setOnFinished(pauseEvent -> createRandomPillar(gameLayout));
//            // Play the PauseTransition
//            pauseTransition.play();
//        });
//    }
//
//
//    private Scene createGameOverScene(Stage stage, int score) {
//        AnchorPane gameOverLayout = new AnchorPane();
//        Scene gameOverScene = new Scene(gameOverLayout, 1200, 600);
//
//        // Load the background image
//        Image temp = new Image(getClass().getResource("/Images/GameOver.png").toExternalForm());
//        ImageView background = new ImageView(temp);
//        background.setFitWidth(1200);
//        background.setFitHeight(600);
//
//        // Add the background image to the layout
//        gameOverLayout.getChildren().add(background);
//
//        // Display the score using a Label
//        Label scoreLabel = new Label("" + score);
//        scoreLabel.setStyle("-fx-font-size: 40; -fx-text-fill: #1b5150; -fx-font-weight: bold;");
//
//        AnchorPane.setTopAnchor(scoreLabel, 242.0);
//        AnchorPane.setLeftAnchor(scoreLabel, 600.0);
//        AnchorPane.setRightAnchor(scoreLabel, 150.0); // Center the label horizontally
//        AnchorPane.setBottomAnchor(scoreLabel, 300.0); // Center the label vertically
//        gameOverLayout.getChildren().add(scoreLabel);
//
//        // Example: Add a circular "Play Again" button at the bottom right corner
//        Circle playAgainButton = new Circle(25, Color.TRANSPARENT); // Adjust the radius and color as needed
//        playAgainButton.setOnMouseClicked(e -> {
//            // Reset game state or perform any necessary actions to restart the game
//            stage.setScene(createGameScene(stage));
//        });
//
//        // Set the position of the circular button to the bottom right corner
//        AnchorPane.setBottomAnchor(playAgainButton, 30.0);
//        AnchorPane.setRightAnchor(playAgainButton, 20.0);
//
//        // Add the circular button to the layout
//        gameOverLayout.getChildren().add(playAgainButton);
//
//        // Example: Add another circular "Home" button at the bottom left corner
//        Circle homeButton = new Circle(25, Color.TRANSPARENT); // Adjust the radius and color as needed
//        homeButton.setOnMouseClicked(e -> {
//            // Go to the home scene
//            stage.setScene(homescene);
//        });
//
//        // Set the position of the home button to the bottom left corner
//        AnchorPane.setBottomAnchor(homeButton, 30.0);
//        AnchorPane.setLeftAnchor(homeButton, 20.0);
//
//        // Add the home button to the layout
//        gameOverLayout.getChildren().add(homeButton);
//
//        return gameOverScene;
//    }
//
//    private void death(){
//        Timeline HerofallingTimeline = new Timeline();
//
//        // Add a KeyFrame to the Timeline
//        KeyFrame keyFramex = new KeyFrame(Duration.seconds(2),
//                new KeyValue(hero.imageView.layoutYProperty(), hero.imageView.getLayoutY() + 500));
//
//        KeyFrame keyFramex2 = new KeyFrame(Duration.seconds(2),
//                new KeyValue(invertedHero.imageView.layoutYProperty(), invertedHero.imageView.getLayoutY() + 500));
//
//        HerofallingTimeline.getKeyFrames().addAll(keyFramex,keyFramex2);
//
//        HerofallingTimeline.setOnFinished(event -> {
//            Scene gameOverScene = createGameOverScene(primaryStage,score);
//            primaryStage.setScene(gameOverScene);
//        });
//
//        HerofallingTimeline.play();
//    }
//
//    private void fallHero(){
//        death();
//
//        //now written code to make the stick fall down
//        double pivotX = stick.getX() + stick.getWidth() / 2;
//        double pivotY = stick.getY() + stick.getHeight();
//
//        // Create a Rotate object
//        Rotate rotate = new Rotate(0, pivotX, pivotY);
//
//        // Apply the initial rotation to the ImageView
//        stick.getTransforms().add(rotate);
//
//        // Create a final Timeline array with a single element
//        final Timeline[] timeline = new Timeline[1];
//
//        // Create a KeyFrame for the falling animation
//        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.01), e -> {
//            if (rotate.getAngle() < 90) {
//                rotate.setAngle(rotate.getAngle() + 2); // Adjust the increment as needed
//            } else {
//                timeline[0].stop();
//            }
//        });
//
//        // Create a Timeline for the falling animation
//        timeline[0] = new Timeline(keyFrame);
//        timeline[0].setCycleCount(Timeline.INDEFINITE);
//        timeline[0].play();
//    }
//
//    public void shiftGamePane(){
//
//        //MAKING TIMELINE FOR MOVING OBJECTS
//        double DistanceToMove = second.getLayoutX() - first.getLayoutX();
//
//        Timeline timeline = new Timeline();
//
//        // Add a KeyFrame to the Timeline
//        KeyFrame keyFrame6 = null;
//        if(cherry != null){
//            keyFrame6 = new KeyFrame(Duration.seconds(2)
//                    ,
//                    new KeyValue(cherry.layoutXProperty(), cherry.getLayoutX() - DistanceToMove));
//        }
//
//        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2),
//                new KeyValue(stick.layoutXProperty(), stick.getLayoutX() - stick.getHeight()));
//
//        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(2),
//                new KeyValue(hero.imageView.layoutXProperty(), hero.imageView.getLayoutX() - DistanceToMove));
//
//        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(2),
//                new KeyValue(first.layoutXProperty(), first.getLayoutX() - DistanceToMove));
//
//        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(2),
//                new KeyValue(second.layoutXProperty(), second.getLayoutX() - DistanceToMove));
//
//        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(2),
//                new KeyValue(invertedHero.imageView.layoutXProperty(), invertedHero.imageView.getLayoutX() - DistanceToMove));
//
//        // Add the KeyFrame to the Timeline
//        if(cherry == null)
//        {
//            timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5);
//        }
//        else{
//            timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6);
//        }
//
//        timeline.play();
//
//        timeline.setOnFinished(event -> {
//            stick.setVisible(false);
//            cherryPosition = -1;
//            cherryPresent = false;
//        });
//
//    }
//    private void animateStick() {
//        if (isGrowing) {
//            Timeline timeline = new Timeline(
//                    new KeyFrame(Duration.millis(8), event -> {
//                        handleStick();
//                    })
//            );
//            timeline.setCycleCount(Timeline.INDEFINITE);
//            timeline.play();
//        }
//
//    }
//
//    private void handleStick() {
//        if (isGrowing && stick != null) {
//            // Calculate the increment in stick's Y position
//            double incrementY = 4;
//
//            // Adjust the stick's Y position and decrease its height
//            stick.setLayoutY(stick.getLayoutY() - incrementY);
//            stick.setHeight(stick.getHeight() + incrementY);
//        }
//    }
//    private void createRandomPillar(AnchorPane gameLayout) {
//        Random random = new Random();
//        double lowerWidth = 10;
//        double upperWidth = 200;
//        double PILLAR_WIDTH = random.nextDouble(upperWidth - lowerWidth) + lowerWidth;
//
//        System.out.println("heyy pillar witdth is "+ PILLAR_WIDTH);
//        Rectangle pillar = new Rectangle(PILLAR_WIDTH, PILLAR_HEIGHT, Color.BLACK);
//        double lowerLimit = (firstPillarWidth+first.getLayoutX()+40);
//        double upperLimit = gameLayout.getWidth()-700;
//
//        double randomXCoordinate = random.nextDouble(upperLimit - lowerLimit) + lowerLimit;
//
//        pillar.setLayoutX(randomXCoordinate);
//        pillar.setLayoutY(330.0);
//        gameLayout.getChildren().add(pillar);
//
//        second = pillar;
//        secondPillarWidth = PILLAR_WIDTH;
//
//        createCherry(gameLayout);
//    }
//
//    private void createCherry(AnchorPane gameLayout){
//        Random random = new Random();
//        //now creating cherry
////        boolean cherryYesOrNo = random.nextBoolean();
//        boolean cherryYesOrNo = true;
//        if((cherryYesOrNo && (second.getLayoutX() - (first.getLayoutX() + first.getWidth())) > 40) ){
//            Image temp3 = new Image(getClass().getResource("/Images/cherry.png").toExternalForm());
//            cherry = new ImageView(temp3);
//            cherry.setFitHeight(15);
//            cherry.setFitWidth(15);
//            double lowerLimitForCherry = (firstPillarWidth + first.getLayoutX()) + 15;
//            double upperLimitForCherry = second.getLayoutX() - 15;
//
//            // Position the cherry between the pillars
//            double cherryXCoordinate = random.nextDouble(upperLimitForCherry - lowerLimitForCherry) + lowerLimitForCherry;
//            cherry.setLayoutX(cherryXCoordinate);
//            cherry.setLayoutY(330);
//            gameLayout.getChildren().add(cherry);
//            cherryPresent = true;
//            cherryPosition = cherryXCoordinate;
//        }
//    }
//}