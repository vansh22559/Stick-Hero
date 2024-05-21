package com.example.practise;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class MediaManager {
    private static MediaManager instance;
    private MediaPlayer mediaPlayer;
    private MediaPlayer backgroundMusicPlayer;
    private AudioClip stickGrowingSound;
    private AudioClip gameOverSound;
    private AudioClip collectCherrySound;

    private MediaManager() {
        // Initialize the background music
        URL musicUrl = getClass().getResource("/Images/Gamesong.mp3");
        if (musicUrl != null) {
            Media media = new Media(musicUrl.toString());
            backgroundMusicPlayer = new MediaPlayer(media);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }

        // Initialize the stick growing sound
        URL stickGrowingSoundUrl = getClass().getResource("/Images/blip.mp3");
        if (stickGrowingSoundUrl != null) {
            stickGrowingSound = new AudioClip(stickGrowingSoundUrl.toString());
        }

        // Initialize the game over sound
        URL gameOverSoundUrl = getClass().getResource("/Images/Gameover.wav");
        if (gameOverSoundUrl != null) {
            gameOverSound = new AudioClip(gameOverSoundUrl.toString());
        }
        URL collectCherrySoundUrl = getClass().getResource("/Images/collectCherry.wav");
        if (collectCherrySoundUrl != null) {
            collectCherrySound = new AudioClip(collectCherrySoundUrl.toString());
        }

        // Initialize the media player for video (if needed)
        String videoPath = "/Images/GameAnimationFinal.mp4"; // Adjust the path as needed
        URL videoUrl = getClass().getResource(videoPath);
        if (videoUrl != null) {
            Media media = new Media(videoUrl.toExternalForm());
            mediaPlayer = new MediaPlayer(media);
        }
    }

    public static MediaManager getInstance() {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public MediaPlayer getBackgroundMusicPlayer() {
        return backgroundMusicPlayer;
    }

    public AudioClip getStickGrowingSound() {
        return stickGrowingSound;
    }

    public AudioClip getGameOverSound() {
        return gameOverSound;
    }
    public AudioClip getCollectCherrySound() {
        return collectCherrySound;
    }


    // Add other methods to control media playback as needed
}