package org.example.tennisscoreboard;

import org.example.tennisscoreboard.models.CurrentMatch;
import org.example.tennisscoreboard.models.Score;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Game {
    private final static Map<UUID, Score> currentMatches = new HashMap<>();

    public static Map<UUID, Score> getCurrentMatches() {
        return currentMatches;
    }

    public static void addMatch(CurrentMatch currentMatch) {
        currentMatches.put(currentMatch.getCurrentMatchId(), currentMatch.getScore());
    }
}
