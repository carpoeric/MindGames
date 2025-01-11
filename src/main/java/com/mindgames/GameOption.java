package com.mindgames;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum GameOption {
    CLASSIC_MODE("0"),
    SINGLE_MIND_MODE("1"),
    DOUBLE_TROUBLE("2"),
    MAIN_MENU("M"),
    QUIT("X"),
    INSTRUCTIONS("Q");

    private final String option;
    private static final Map<String, GameOption> OPTION_MAP = new HashMap<>();

    static {
        for (GameOption gameOption : GameOption.values()) {
            OPTION_MAP.put(gameOption.option, gameOption);
        }
    }

    GameOption(String option) {
        this.option = option;
    }

    public static Optional<GameOption> fromString(String option) {
        return Optional.ofNullable(OPTION_MAP.get(option.toUpperCase()));
    }
}