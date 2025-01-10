package com.mindgames;

import java.util.Scanner;

public class Main {
    private static final String OPTION_CLASSIC_MODE = "1";
    private static final String OPTION_DOUBLE_TROUBLE = "2";
    private static final String OPTION_MAIN_MENU = "M";
    private static final String OPTION_QUIT = "X";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameApp.mainMenu();

        while (true) {
            String scanInput = getUserInput(scanner);

            if (scanInput.equals(OPTION_CLASSIC_MODE)) {
                new SingleCombinationGame().play(scanner);
            } else if (scanInput.equals(OPTION_DOUBLE_TROUBLE)) {
                new DoubleCombinationGame().play(scanner);
            } else if (scanInput.equals(OPTION_MAIN_MENU)) {
                System.out.println("Returning to the main menu!");
                GameApp.mainMenu();
            } else if (scanInput.equals(OPTION_QUIT)) {
                System.out.println("Quitting the game... Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please enter '1', '2', 'M', or 'X'.");
            }
        }
    }

    private static String getUserInput(Scanner scanner) {
        return scanner.nextLine().trim().toUpperCase();
    }
}