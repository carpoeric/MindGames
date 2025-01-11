package com.mindgames;

public class User {
    private String username;
    private int score;
    private int gamesPlayed;

    // Constructor
    public User(String username) {
        this.username = username;
        this.score = 0;
        this.gamesPlayed = 0;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Username: " + username + " | Score: " + score + " | Games Played: " + gamesPlayed;
    }
}