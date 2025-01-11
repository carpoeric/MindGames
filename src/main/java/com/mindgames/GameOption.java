package com.mindgames;

import java.util.Optional;

public enum GameOption {
    INSTRUCTIONS("Q"),
    SINGLE_MIND_MODE("1"),
    DOUBLE_TROUBLE("2"),
    CLASSIC_MODE("0"),
    DISPLAY_USER_DATA("D"),
    QUIT("X");

    private final String option;

    GameOption(String option) {
        this.option = option;
    }

    public static Optional<GameOption> fromString(String option) {
        for (GameOption gameOption : values()) {
            if (gameOption.option.equals(option)) {
                return Optional.of(gameOption);
            }
        }
        return Optional.empty();
    }
}