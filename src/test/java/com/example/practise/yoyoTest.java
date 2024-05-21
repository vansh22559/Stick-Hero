package com.example.practise;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class yoyoTest {

    private yoyo game;

    @BeforeEach
    public void setUp() {
        game = new yoyo();
    }

    @Test
    public void testInitialScore() {
        assertEquals(0, game.getScore());
    }

    @Test
    void testInitialCherryCount() {
        // Check if the initial cherry count is 0
        assertEquals(0, game.getCherryCount(), "Initial cherry count should be 0");
    }
    @Test
    void testSerializationDeserialization() {
        // Create a GameData object
        GameData originalData = new GameData(100, 5, 150);

        // Serialize the object
        yoyo game = new yoyo(); // create an instance of your application class to access the serialization methods
        game.serializeGameData(originalData);

        // Deserialize the object
        GameData deserializedData = game.deserializeGameData();

        // Compare values
        assertEquals(originalData.getScore(), deserializedData.getScore());
        assertEquals(originalData.getCherryCount(), deserializedData.getCherryCount());
        assertEquals(originalData.getHighScore(), deserializedData.getHighScore());
    }


    // Add more test methods for other functionalities as needed
}
