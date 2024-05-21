package com.example.practise;
import java.io.Serializable;

public class GameData implements Serializable {
    private static final long serialVersionUID = 1L;

    private int score;
    private int cherryCount;
    private int highScore;  // New field for high score

    public GameData(int score, int cherryCount, int highScore) {
        this.score = score;
        this.cherryCount = cherryCount;
        this.highScore = highScore;
    }

    // Getters and setters for score, cherryCount, and highScore

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCherryCount() {
        return cherryCount;
    }

    public void setCherryCount(int cherryCount) {
        this.cherryCount = cherryCount;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
