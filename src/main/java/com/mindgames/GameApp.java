package com.mindgames;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GameApp {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

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

        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Q")) {
                showInstructions(scanner);
                break;
            } else if (input.equals("1")) {
                new SingleMindGame().play(scanner);
                break;
            } else if (input.equals("2")) {
                new DoubleCombinationGame().play(scanner);
                break;
            } else if (input.equals("0")) {
                new ClassicModeGame().play(scanner);
                break;
            } else if (input.equals("X")) {
                System.out.println("Quitting the game. Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please enter 'Q', '1', '2', '0', or 'X'.");
            }
        }
    }

    private static void showInstructions(Scanner scanner) {
        System.out.println("\n-----INTRODUCTION & HINTS-----");
        System.out.println("CLASSIC MODE: ");
        System.out.println("You will have 10 tries to figure out the four digit number combination I am thinking of with minimal hints!");
        System.out.println("Each guess should consist of any digits from 0-7 (none of my combos use 8 or 9!). Hit the enter button to submit.");
        System.out.println("If you're stumped, you can press H for a hint! (one number in it's correct location)");

        System.out.println("\nSINGLE MIND MODE: ");
        System.out.println("You will have 10 tries to figure out the four digit number combination I am thinking of.");
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

        label:
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "M":
                    mainMenu();
                    break label;
                case "1":
                    new SingleMindGame().play(scanner);
                    break label;
                case "2":
                    new DoubleCombinationGame().play(scanner);
                    break label;
                case "0":
                    new ClassicModeGame().play(scanner);
                    break label;
                case "X":
                    System.out.println("Quitting the game. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please enter 'M', '1', '2', '3', or 'X'.");
                    break;
            }
        }
    }

    public static String randomNumber() {
        String apiUrl = "https://www.random.org/integers/?num=4&min=0&max=7&col=1&base=10&format=plain&rnd=new";
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
            System.err.println("Error fetching random numbers: " + e.getMessage());
        }

        return randomNumbers.toString();
    }
}