package com.mindgames;

import java.util.Scanner;
import java.util.Arrays;
import static com.mindgames.GameApp.*;

public class ClassicModeGame {
    private char[] arrayRandomNumber;

    public void play(Scanner scanner, User currentUser) {
        String randomNumber = randomNumber();
        arrayRandomNumber = randomNumber.toCharArray();
        System.out.println("\nWelcome to Classic Mode!\nPlease type your first guess: ");
        System.out.println("If you're stumped, you can press 'H' for a hint! (one number in its correct location)");

        int tries = 1;
        while (tries <= 10) {
            String numberInput = getValidGuess(scanner);
            if (numberInput.equals("H")) {
                provideHint();
                continue;
            }

            char[] arrayNumberTyped = numberInput.toCharArray();
            System.out.println("Guess " + tries + "/10: " + numberInput);

            if (Arrays.equals(arrayNumberTyped, arrayRandomNumber)) {
                System.out.println("\n\nAwesome! You solved that in \u001B[32m" + tries + "\u001B[0m tries.");
                currentUser.setScore(currentUser.getScore() + 30);
                currentUser.setGamesPlayed(currentUser.getGamesPlayed() + 1);
                UserDataManager.saveUser(currentUser);
                break;
            } else {
                printMinimalFeedback(arrayNumberTyped, arrayRandomNumber);
                System.out.print("Enter guess: ");
            }

            if (tries == 10) {
                System.out.println("\nLooks like you didn't get it. Better luck next time!");
                currentUser.setScore(currentUser.getScore() + 5);
                currentUser.setGamesPlayed(currentUser.getGamesPlayed() + 1);
                UserDataManager.saveUser(currentUser);
            }
            tries++;
        }
        System.out.print("The correct combination is... ");
        printColoredOutput(arrayRandomNumber, ANSI_GREEN_BACKGROUND);

        System.out.println("\nWould you like to play again?");
        System.out.println("Enter '0' to play Classic Mode again!");
        System.out.println("Enter '1' to play Single Mind Mode");
        System.out.println("Enter '2' to play DOUBLE TROUBLE");
        System.out.println("Enter 'M' to return to the main menu.");
        System.out.println("Enter 'X' to quit the game.");
        System.out.println(ANSI_BLACK_BACKGROUND + "ENTER HERE:" + ANSI_RESET);
        String scanInput = scanner.nextLine().trim().toUpperCase();
        if (scanInput.equals("1")) {
            new SingleMindGame().play(scanner, currentUser);
        } else if (scanInput.equals("2")) {
            new DoubleCombinationGame().play(scanner, currentUser);
        } else if (scanInput.equals("0")) {
            new ClassicModeGame().play(scanner, currentUser);
        } else if (scanInput.equals("M")) {
            GameApp.mainMenu(currentUser);
        } else if (scanInput.equals("X")) {
            System.out.println("Quitting the game. Goodbye!");
            System.exit(0);
        }
    }

    private String getValidGuess(Scanner scanner) {
        String numberInput = scanner.nextLine().trim().toUpperCase();
        while (!(numberInput.length() == 4 && numberInput.matches("[0-7]+")) && !numberInput.equals("H")) {
            System.out.print("Invalid guess... Give it another go: ");
            numberInput = scanner.nextLine().trim().toUpperCase();
        }
        return numberInput;
    }

    private void provideHint() {
        int randomIndex = (int) (Math.random() * arrayRandomNumber.length);
        System.out.println("Hint: The digit at position " + (randomIndex + 1) + " is " + arrayRandomNumber[randomIndex]);
    }

    private void printMinimalFeedback(char[] arrayNumberTyped, char[] arrayRandomNumber) {
        int correctNumbers = 0;
        int correctLocations = 0;
        boolean[] matched = new boolean[4];
        boolean[] guessed = new boolean[4];

        for (int i = 0; i < 4; i++) {
            if (arrayNumberTyped[i] == arrayRandomNumber[i]) {
                correctLocations++;
                correctNumbers++;
                matched[i] = true;
                guessed[i] = true;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (!guessed[i]) {
                for (int j = 0; j < 4; j++) {
                    if (!matched[j] && arrayNumberTyped[i] == arrayRandomNumber[j]) {
                        correctNumbers++;
                        matched[j] = true;
                        break;
                    }
                }
            }
        }

        System.out.println(correctNumbers + " correct number(s) and " + correctLocations + " correct location(s)");
    }

    private void printColoredOutput(char[] array, String color) {
        for (char c : array) {
            System.out.print(color + " " + c + " " + ANSI_RESET);
        }
        System.out.println();
    }
}