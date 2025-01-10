package com.mindgames;

import java.util.*;
import static com.mindgames.GameApp.*;

public class Main {
    public static void main(String[] args) {
        mainMenu();

        Scanner scanner = new Scanner(System.in);
        String scanInput = scanner.nextLine().trim();

        while (scanInput.equals("1")) {
            String numberInput;
            String randomNumber = randomNumber();
            char[] arrayRandomNumber = randomNumber.toCharArray();
            System.out.println("\nGUESSING TIME!\nPlease type your first guess: ");

            int tries = 1;
            while (tries <= 10) {
                numberInput = scanner.nextLine().trim();
                while (numberInput.length() != 4 || !numberInput.matches("[0-7]+")) {
                    System.out.print("Invalid guess... Give it another go: ");
                    numberInput = scanner.nextLine().trim();
                }
                char[] arrayNumberTyped = numberInput.toCharArray();

                System.out.print("Guess " + tries + "/10: ");
                if (Arrays.equals(arrayNumberTyped, arrayRandomNumber)) {
                    for (int i = 0; i < 4; i++) {
                        System.out.print(ANSI_GREEN_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
                    }
                    System.out.println("\n\nAwesome! You solved that in \u001B[32m" + tries + "\u001B[0m tries.");
                    break;
                } else {
                    for (int i = 0; i < 4; i++) {
                        int countCharTyped = numberInput.length() - numberInput.replaceAll(String.valueOf(arrayNumberTyped[i]), "").length();
                        int countCharRandom = randomNumber.length() - randomNumber.replaceAll(String.valueOf(arrayNumberTyped[i]), "").length();
                        int colorBlocks = countCharRandom;

                        if (randomNumber.contains(String.valueOf(arrayNumberTyped[i]))) {
                            if (arrayNumberTyped[i] == arrayRandomNumber[i]) {
                                System.out.print(ANSI_GREEN_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
                            } else {
                                if (countCharTyped <= countCharRandom) {
                                    System.out.print(ANSI_YELLOW_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
                                } else {
                                    for (int j = 0; j <= i; j++) {
                                        if (arrayNumberTyped[j] == arrayNumberTyped[i]) colorBlocks--;
                                    }
                                    for (int k = 0; k < 4; k++) {
                                        if (arrayNumberTyped[k] == arrayRandomNumber[i]) colorBlocks--;
                                    }
                                    if (colorBlocks > 0) {
                                        System.out.print(ANSI_YELLOW_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
                                    } else {
                                        if (countCharRandom > 0) {
                                            int checkPreviouslyShown = 0;
                                            int countGreenBlocks = 0;
                                            for (int j = 0; j < i; j++) {
                                                if (arrayNumberTyped[j] == arrayNumberTyped[i]) {
                                                    checkPreviouslyShown++;
                                                }
                                            }
                                            for (int k = 0; k < 4; k++) {
                                                if (arrayNumberTyped[k] == arrayRandomNumber[k] && arrayNumberTyped[i] == arrayNumberTyped[k]) countGreenBlocks++;
                                            }
                                            if (checkPreviouslyShown == 0 && countGreenBlocks == 0) System.out.print(ANSI_YELLOW_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
                                            else System.out.print(ANSI_BLACK_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
                                        } else {
                                            System.out.print(ANSI_BLACK_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.print(ANSI_BLACK_BACKGROUND + " " + arrayNumberTyped[i] + " " + ANSI_RESET);
                        }
                    }
                    System.out.println();
                }
                if (tries == 10 && !Arrays.equals(arrayNumberTyped, arrayRandomNumber)) {
                    System.out.println("\nLooks like you didn't get it. Better luck next time!");
                }
                tries++;
            }
            System.out.print("The correct combination is... ");
            for (int i = 0; i < 4; i++) {
                System.out.print(ANSI_GREEN_BACKGROUND + " " + arrayRandomNumber[i] + " " + ANSI_RESET);
            }

            System.out.println("\n\nEnter 1 to go again!\nEnter 0 to exit.");
            scanInput = scanner.nextLine().trim();
        }

        System.out.println("Seems like something went wrong. Exiting program!");
    }
}