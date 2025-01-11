package com.mindgames;

import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_BLACK_BACKGROUND + " __    __   __   __   __   _____    ______   ______   __    __   ______   ______    " + ANSI_RESET);
        System.out.println(ANSI_BLACK_BACKGROUND + "/\\ \"-./  \\ /\\ \\ /\\ \"-.\\ \\ /\\  __-. /\\  ___\\ /\\  __ \\ /\\ \"-./  \\ /\\  ___\\ /\\  ___\\   " + ANSI_RESET);
        System.out.println(ANSI_BLACK_BACKGROUND + "\\ \\ \\-./\\ \\\\ \\ \\\\ \\ \\-.  \\\\ \\ \\/\\ \\\\ \\ \\__ \\\\ \\  __ \\\\ \\ \\-./\\ \\\\ \\  __\\ \\ \\___  \\  " + ANSI_RESET);
        System.out.println(ANSI_BLACK_BACKGROUND + " \\ \\_\\ \\ \\_\\\\ \\_\\\\ \\_\\\\\"\\_\\\\ \\____- \\ \\_____\\\\ \\_\\ \\_\\\\ \\_\\ \\ \\_\\\\ \\_____\\\\/\\_____\\ " + ANSI_RESET);
        System.out.println(ANSI_BLACK_BACKGROUND + "  \\/_/  \\/_/ \\/_/ \\/_/ \\/____/  \\/_____/ \\/_/\\/_/ \\/_/  \\/_/ \\/_____/ \\/_____/ " + ANSI_RESET);
        System.out.println(ANSI_BLACK_BACKGROUND + "                                                                                    " + ANSI_RESET);
        System.out.println("\u001B[32mPlease enter your username! If you don't have one, a new user will be created.\u001B[0m");
        System.out.println(ANSI_BLACK_BACKGROUND + "ENTER HERE:" + ANSI_RESET);
        String username = scanner.nextLine().trim();
        User currentUser = UserDataManager.loadUser(username);

        if (currentUser == null) {
            currentUser = new User(username);
        }

        GameApp.mainMenu(currentUser);
        scanner.close();
    }
}