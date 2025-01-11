package com.mindgames;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameApp {
    private static final Logger LOGGER = Logger.getLogger(GameApp.class.getName());
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

  // API URL
    private static String apiUrl = "https://www.random.org/integers/?num=4&min=0&max=7&col=1&base=10&format=plain&rnd=new";

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("\u001B[32mWelcome to MINDGAMES!\u001B[0m");
        System.out.println("MINDGAMES is a fun and challenging game where you have to guess a combination of numbers!");
        System.out.println("\nEnter 'Q' if it's your first time! Here, you can see the instructions and hint example.");
        System.out.println("Enter '0' to start Classic Mode.");
        System.out.println("Enter '1' to start Single Mind Mode.");
        System.out.println("Enter '2' to start Double Trouble.");
        System.out.println("Enter 'X' to quit the game.");
        System.out.println(ANSI_BLACK_BACKGROUND + "ENTER HERE:" + ANSI_RESET);

        handleUserInput(scanner);
    }

    private static void handleUserInput(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            Optional<GameOption> option = GameOption.fromString(input);

            if (!option.isPresent()) {
                System.out.println("Invalid input. Please enter 'Q', '1', '2', '0', or 'X'.");
                continue;
            }

            switch (option.get()) {
                case INSTRUCTIONS:
                    showInstructions(scanner);
                    return;
                case SINGLE_MIND_MODE:
                    new SingleMindGame().play(scanner);
                    return;
                case DOUBLE_TROUBLE:
                    new DoubleCombinationGame().play(scanner);
                    return;
                case CLASSIC_MODE:
                    new ClassicModeGame().play(scanner);
                    return;
                case QUIT:
                    System.out.println("Quitting the game. Goodbye!");
                    System.exit(0);
            }
        }
    }

    private static void showInstructions(Scanner scanner) {
        System.out.println("\n-----INTRODUCTION & HINTS-----");
        System.out.println("CLASSIC MODE: ");
        System.out.println("You will have 10 tries to figure out the four-digit number combination I am thinking of with minimal hints!");
        System.out.println("Each guess should consist of any digits from 0-7 (none of my combos use 8 or 9!). Hit the enter button to submit.");
        System.out.println("If you're stumped, you can press H for a hint! (one number in its correct location)");

        System.out.println("\nSINGLE MIND MODE: ");
        System.out.println("You will have 10 tries to figure out the four-digit number combination I am thinking of.");
        System.out.println("Once you've made a guess, I will give you some hints to help you figure out the correct combination.");

        System.out.println("\nHow the hints work for this mode:");
        System.out.println(ANSI_GREEN_BACKGROUND + " 0 " + ANSI_YELLOW_BACKGROUND + " 1 " + ANSI_BLACK_BACKGROUND + " 2 " + " 3 " + ANSI_RESET);
        System.out.println("Number " + ANSI_GREEN_BACKGROUND + " 0 " + ANSI_RESET + " is in the combo and in the correct spot.");
        System.out.println("Number " + ANSI_YELLOW_BACKGROUND + " 1 " + ANSI_RESET + " is in the combo but in the wrong spot.");
        System.out.println("Number " + ANSI_BLACK_BACKGROUND + " 2 " + ANSI_RESET + " & Number " + ANSI_BLACK_BACKGROUND + " 3 " + ANSI_RESET  + " are not in the combo at all.");

        System.out.println("\nDOUBLE TROUBLE MODE: ");
        System.out.println("If you're up for the challenge, try out Double Trouble!");
        System.out.println("It's like Single Mind except with TWO combinations. Double the combo, double the fun!");
        System.out.println("Here you will have to guess two combinations at the same time, but you will have 12 tries to do so.");

        System.out.println("\nEnter 'M' to return to the main menu.");
        System.out.println("Enter '0' to start Classic Mode.");
        System.out.println("Enter '1' to start Single Mind Mode.");
        System.out.println("Enter '2' to start Double Trouble Mode.");
        System.out.println("Enter 'X' to quit the game.");
        System.out.println(ANSI_BLACK_BACKGROUND + "ENTER HERE:" + ANSI_RESET);

        handleUserInput(scanner);
    }

    public static String randomNumber() {
        StringBuilder randomNumbers = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                randomNumbers.append(scanner.nextLine().trim());
            }
            scanner.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error fetching random numbers", e);
        }

        return randomNumbers.toString();
    }
}