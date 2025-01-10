package com.mindgames;

import java.util.Scanner;
import java.util.Arrays;
import static com.mindgames.GameApp.*;

public class SingleCombinationGame {
    public void play(Scanner scanner) {
        String randomNumber = randomNumber();
        char[] arrayRandomNumber = randomNumber.toCharArray();
        System.out.println("\nClassic Mode!\nPlease type your first guess: ");

        int tries = 1;
        while (tries <= 10) {
            String numberInput = getValidGuess(scanner);
            char[] arrayNumberTyped = numberInput.toCharArray();

            System.out.println("Guess " + tries + "/10: " + numberInput);

            System.out.print("Combination: ");
            if (Arrays.equals(arrayNumberTyped, arrayRandomNumber)) {
                printColoredOutput(arrayNumberTyped, ANSI_GREEN_BACKGROUND);
                System.out.println("\n\nAwesome! You solved that in \u001B[32m" + tries + "\u001B[0m tries.");
                break;
            } else {
                printHints(arrayNumberTyped, arrayRandomNumber);
                System.out.print("Enter guess: ");
            }

            if (tries == 10) {
                System.out.println("\nLooks like you didn't get it. Better luck next time!");
            }
            tries++;
        }
        System.out.print("The correct combination is... ");
        printColoredOutput(arrayRandomNumber, ANSI_GREEN_BACKGROUND);

        System.out.println("\nWould you like to play again?");
        System.out.println("Enter '1' to play Classic Mode again!");
        System.out.println("Enter '2' to play DOUBLE TROUBLE again!");
        System.out.println("Enter 'M' to return to the main menu.");
        System.out.println(ANSI_BLACK_BACKGROUND + "ENTER HERE:" + ANSI_RESET);
        String scanInput = scanner.nextLine().trim().toUpperCase();
        if (scanInput.equals("1")) {
            new SingleCombinationGame().play(scanner);
        } else if (scanInput.equals("2")) {
            play(scanner);
        } else if (scanInput.equals("M")) {
            GameApp.mainMenu();
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