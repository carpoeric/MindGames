package com.mindgames;

import java.util.Scanner;
import java.util.Arrays;
import static com.mindgames.GameApp.*;

public class DoubleCombinationGame {
    public void play(Scanner scanner) {
        String randomNumber1 = randomNumber();
        String randomNumber2 = randomNumber();
        char[] arrayRandomNumber1 = randomNumber1.toCharArray();
        char[] arrayRandomNumber2 = randomNumber2.toCharArray();
        boolean solved1 = false;
        boolean solved2 = false;
        System.out.println("\nWelcome to DOUBLE TROUBLE!\nPlease type your first guess: ");

        int tries = 1;
        while (tries <= 12) {
            String numberInput = getValidGuess(scanner);
            char[] arrayNumberTyped = numberInput.toCharArray();

            System.out.println("Guess " + tries + "/12: " + numberInput);

            System.out.print("Combination 1: ");
            if (!solved1) {
                solved1 = Arrays.equals(arrayNumberTyped, arrayRandomNumber1);
                if (solved1) {
                    printColoredOutput(arrayNumberTyped, ANSI_GREEN_BACKGROUND);
                } else {
                    printHints(arrayNumberTyped, arrayRandomNumber1);
                }
            } else {
                printColoredOutput(arrayRandomNumber1, ANSI_GREEN_BACKGROUND);
            }

            System.out.print("Combination 2: ");
            if (!solved2) {
                solved2 = Arrays.equals(arrayNumberTyped, arrayRandomNumber2);
                if (solved2) {
                    printColoredOutput(arrayNumberTyped, ANSI_GREEN_BACKGROUND);
                } else {
                    printHints(arrayNumberTyped, arrayRandomNumber2);
                }
            } else {
                printColoredOutput(arrayRandomNumber2, ANSI_GREEN_BACKGROUND);
            }

            if (solved1 && solved2) {
                System.out.println("\n\nAwesome! You solved both combinations in \u001B[32m" + tries + "\u001B[0m tries.");
                break;
            } else {
                System.out.print("Enter guess: ");
            }

            if (tries == 12) {
                System.out.println("\nLooks like you didn't get it. Better luck next time!");
            }
            tries++;
        }
        System.out.println("\nWould you like to play again?");
        System.out.println("Enter '0' to start Classic Mode");
        System.out.println("Enter '1' to play Single Mind Mode");
        System.out.println("Enter '2' to play DOUBLE TROUBLE again!");
        System.out.println("Enter 'M' to return to the main menu.");
        System.out.println("Enter 'X' to quit the game.");
        System.out.println(ANSI_BLACK_BACKGROUND + "ENTER HERE:" + ANSI_RESET);
        String scanInput = scanner.nextLine().trim().toUpperCase();
        if (scanInput.equals("1")) {
            new SingleMindGame().play(scanner);
        } else if (scanInput.equals("2")) {
            new DoubleCombinationGame().play(scanner);
        } else if (scanInput.equals("M")) {
            GameApp.mainMenu();
        } else if (scanInput.equals("0")) {
            new ClassicModeGame().play(scanner);
        } else if (scanInput.equals("X")) {
            System.out.println("Quitting the game. Goodbye!");
            System.exit(0);
        }
    }

    private String getValidGuess(Scanner scanner) {
        String numberInput = scanner.nextLine().trim();
        while (numberInput.length() != 4 || !numberInput.matches("[0-7]+")) {
            System.out.print("Invalid guess... Give it another go: ");
            numberInput = scanner.nextLine().trim();
        }
        return numberInput;
    }

    private void printColoredOutput(char[] array, String color) {
        for (char c : array) {
            System.out.print(color + " " + c + " " + ANSI_RESET);
        }
        System.out.println();
    }

    private void printHints(char[] arrayNumberTyped, char[] arrayRandomNumber) {
        for (int i = 0; i < 4; i++) {
            if (arrayNumberTyped[i] == arrayRandomNumber[i]) {
                System.out.print(ANSI_GREEN_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
            } else if (new String(arrayRandomNumber).contains(String.valueOf(arrayNumberTyped[i]))) {
                System.out.print(ANSI_YELLOW_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
            } else {
                System.out.print(ANSI_BLACK_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
            }
        }
        System.out.println();
    }
}