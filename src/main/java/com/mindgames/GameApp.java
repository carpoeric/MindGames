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
        System.out.println();
        System.out.println("\u001B[32mWelcome to MINDGAMES!\u001B[0m");
        System.out.println("You will have 10 tries to figure out the four digit number combination I am thinking of.");
        System.out.println("Each guess should consist of any digits from 0-7 (none of my combos use 8 or 9!). Hit the enter button to submit.");
        System.out.println("Once you've made a guess, I will give you some hints to help you figure out the correct combination.");
        System.out.println("\nHere's what the hints look like:");
        System.out.println(ANSI_GREEN_BACKGROUND + " 0 " + ANSI_YELLOW_BACKGROUND + " 1 " + ANSI_BLACK_BACKGROUND + " 2 " + " 3 " + ANSI_RESET);
        System.out.println("Number " + ANSI_GREEN_BACKGROUND + " 0 " + ANSI_RESET + " is in the combo and in the correct spot.");
        System.out.println("Number " + ANSI_YELLOW_BACKGROUND + " 1 " + ANSI_RESET + " is in the combo but in the wrong spot.");
        System.out.println("Number " + ANSI_BLACK_BACKGROUND + " 2 " + ANSI_RESET + " & Number " + ANSI_BLACK_BACKGROUND + " 3 " + ANSI_RESET  + " are not in the combo at all.");
        System.out.println("\nIf you're up for the challenge, We have a harder mode called Double Trouble!");
        System.out.println("Here you will have to guess two combinations at the same time, but you will have 12 tries to do so.");
        System.out.println("\nEnter the number 1 to start Classic Mode.");
        System.out.println("Enter the number 2 to start the Double Trouble Mode.");
        System.out.println(ANSI_BLACK_BACKGROUND + "ENTER HERE:" + ANSI_RESET);
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