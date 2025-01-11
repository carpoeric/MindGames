package com.mindgames;

public class User {
    private String username;
    private int score;
    private int gamesPlayed;

    public User(String username) {
        this.username = username;
        this.score = 0;
        this.gamesPlayed = 0;
    }

    public String getUsername() {
        return username;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", gamesPlayed=" + gamesPlayed +
                '}';
    }
}