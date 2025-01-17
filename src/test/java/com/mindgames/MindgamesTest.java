package com.mindgames;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class MindgamesTest {

    private SingleMindGame singleMindGame;
    private ClassicModeGame classicModeGame;

    @BeforeEach
    void setUp() {
        singleMindGame = new SingleMindGame();
        classicModeGame = new ClassicModeGame();
    }

    /**
     * Tests that the randomNumber method returns a valid number.
     * The returned number should be a string of 4 digits from 0 to 7.
     */
    @Test
    void randomNumberShouldReturnValidNumber() {
        String randomNumber = GameApp.randomNumber();
        assertNotNull(randomNumber);
        assertEquals(4, randomNumber.length());
        assertTrue(randomNumber.matches("[0-7]+"));
    }

    /**
     * Tests that the randomNumber method handles network failures gracefully.
     */
    @Test
    void randomNumberShouldHandleNetworkFailures() throws NoSuchFieldException, IllegalAccessException {
        Field apiUrlField = GameApp.class.getDeclaredField("apiUrl");
        apiUrlField.setAccessible(true);
        String originalApiUrl = (String) apiUrlField.get(null);
        apiUrlField.set(null, "https://invalid.url");

        String randomNumber = GameApp.randomNumber();
        assertEquals("", randomNumber); // expects empty
        apiUrlField.set(null, originalApiUrl);
    }

    /**
     * Tests that the getValidGuess method in SingleMindGame handles invalid input and returns a valid guess.
     */
    @Test
    void testSingleMindGameGetValidGuessHandlesInvalidInput() throws Exception {
        String input = "abcd\n1234\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        java.lang.reflect.Method method = SingleMindGame.class.getDeclaredMethod("getValidGuess", Scanner.class);
        method.setAccessible(true);
        String guess = (String) method.invoke(singleMindGame, scanner);
        assertEquals("1234", guess);
    }

    /**
     * Tests that the getValidGuess method in ClassicModeGame handles the hint input and returns the hint.
     */
    @Test
    void testClassicModeGameGetValidGuessHandlesHintInput() throws Exception {
        String input = "H\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        java.lang.reflect.Method method = ClassicModeGame.class.getDeclaredMethod("getValidGuess", Scanner.class);
        method.setAccessible(true);
        String guess = (String) method.invoke(classicModeGame, scanner);
        assertEquals("H", guess);
    }
}