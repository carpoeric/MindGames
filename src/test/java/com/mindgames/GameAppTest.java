package com.mindgames;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class GameAppTest {

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
     * Tests that the fromString method of GameOption returns the correct option.
     * The method should return the corresponding GameOption for valid inputs and null for invalid inputs.
     */
    @Test
    void gameOptionFromStringShouldReturnCorrectOption() {
        assertEquals(GameOption.CLASSIC_MODE, GameOption.fromString("0").orElse(null));
        assertEquals(GameOption.SINGLE_MIND_MODE, GameOption.fromString("1").orElse(null));
        assertEquals(GameOption.DOUBLE_TROUBLE, GameOption.fromString("2").orElse(null));
        assertEquals(GameOption.MAIN_MENU, GameOption.fromString("M").orElse(null));
        assertEquals(GameOption.QUIT, GameOption.fromString("X").orElse(null));
        assertEquals(GameOption.INSTRUCTIONS, GameOption.fromString("Q").orElse(null));
        assertNull(GameOption.fromString("invalid").orElse(null));
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
}