package com.mindgames;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameApp.mainMenu();

        Scanner scanner = new Scanner(System.in);
        String scanInput = scanner.nextLine().trim();

        SingleCombinationGame singleGame = new SingleCombinationGame();
        DoubleCombinationGame doubleGame = new DoubleCombinationGame();

        while (scanInput.equals("1") || scanInput.equals("2")) {
            if (scanInput.equals("1")) {
                singleGame.play(scanner);
            } else if (scanInput.equals("2")) {
                doubleGame.play(scanner);
            }

            System.out.println("\nEnter 1 to play the single combination game again!");
            System.out.println("Enter 2 to play the double combination game again!");
            System.out.println("Enter 0 to exit.");
            scanInput = scanner.nextLine().trim();
        }

        System.out.println("Exiting program!");
    }
}