package com.mindgames;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameApp
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    public static void mainMenu() {
    System.out.println();
    System.out.println("\u001B[32mWelcome to MINDGAMES!\u001B[0m");
    System.out.println("You will have 10 tries to figure out the number combination I'm thinking of.");
    System.out.println("Each guess only consists of the digits 0-7 (no using 8 or 9!). Hit the enter button to submit.");
    System.out.println("Once you've made a guess, I'll give you some hints to help you figure out the correct combination.");
    System.out.println("\nHere's what the hints mean:");
    System.out.println(ANSI_GREEN_BACKGROUND + " 0 " + ANSI_YELLOW_BACKGROUND + " 1 " + ANSI_BLACK_BACKGROUND + " 2 " + " 3 " + ANSI_RESET);
    System.out.println("Number " + ANSI_GREEN_BACKGROUND + " 0 " + ANSI_RESET + " is in the combo and in the correct spot.");
    System.out.println("Number " + ANSI_YELLOW_BACKGROUND + " 1 " + ANSI_RESET + " is in the combo but in the wrong spot.");
    System.out.println("Numbers " + ANSI_BLACK_BACKGROUND + " 2 " + ANSI_RESET + "AND " + ANSI_BLACK_BACKGROUND + " 3 " + ANSI_RESET  + "are not in the combo at all.");
    System.out.println("\nIf you're up for the challenge, please drop the number 1 to start:");
    }

    public static String[] randomNumber() {
    // TODO: API calls ----- https://www.random.org/integers
    String path = "src/main/resources/4digitcombos0to7.txt";
    String contents = null;
    try {
        contents = new String(Files.readAllBytes(Paths.get(path)));
    } catch (IOException e) {
        e.printStackTrace();
    }
    assert contents != null;
    return contents.split("\\r?\\n");
}
}

