package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.exceptions.ThereIsNoSuchCurrentMatchException;
import org.example.tennisscoreboard.models.MatchScoreModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }

    // Private constructor to prevent instantiation

    private OngoingMatchesService() {}

    private final Map<UUID, MatchScoreModel> currentMatches = new ConcurrentHashMap<>();

    public Map<UUID, MatchScoreModel> getCurrentMatches() {
        return currentMatches;
    }

    public void addMatch(MatchScoreModel matchScoreModel) {
        currentMatches.put(matchScoreModel.getMatchId(), matchScoreModel);
    }

    public void deleteMatch(MatchScoreModel matchScoreModel) {
        currentMatches.remove(matchScoreModel.getMatchId(), matchScoreModel);
    }

    public MatchScoreModel getCurrentMatchByUUID(UUID uuid) {
        if (currentMatches.containsKey(uuid)) {
            return currentMatches.get(uuid);
        } else {
            throw new ThereIsNoSuchCurrentMatchException();
        }
    }

    public boolean containsMatch(MatchScoreModel matchScoreModel) {
        return currentMatches.containsKey(matchScoreModel.getMatchId());
    }

    public MatchScoreModel getCurrentMatchScore(UUID uuid) {
        return getCurrentMatchByUUID(uuid);
    }
}
