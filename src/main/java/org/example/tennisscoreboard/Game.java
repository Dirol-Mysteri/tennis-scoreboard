package org.example.tennisscoreboard;

import org.example.tennisscoreboard.models.CurrentMatch;
import org.example.tennisscoreboard.models.Score;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final static Map<CurrentMatch, Score> currentMatches = new HashMap<>();

    public static Map<CurrentMatch, Score> getCurrentMatches() {
        return currentMatches;
    }

    public static void addMatch(CurrentMatch currentMatch) {
        currentMatches.put(currentMatch, currentMatch.getScore());
    }

    public static CurrentMatch getMatch(CurrentMatch currentMatch) {
        return currentMatches.get(currentMatch) == null ? null : currentMatch;
    }

    public static boolean containsMatch(CurrentMatch currentMatch) {

        if (currentMatches.containsKey(currentMatch)) {
            return true;
        }
        return false;
    }
}
