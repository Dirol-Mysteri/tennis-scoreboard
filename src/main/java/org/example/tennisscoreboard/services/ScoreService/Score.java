package org.example.tennisscoreboard.services.ScoreService;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {
    private final List<T> playerScores = new ArrayList<>();

    public Score() {
        playerScores.add(getZeroScore());
        playerScores.add(getZeroScore());
    }

    protected abstract T getZeroScore();

    abstract State pointWon(int playerNumber);

    public T getPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber);
    }

    public T getOppositePlayerScore(int playerNumber) {
        return getPlayerScore(playerNumber == 0 ? 1 : 0);
    }

    public void setPlayerScore(int playerNumber, T score) {
        playerScores.set(playerNumber, score);
    }

    public void setOppositePlayerScore(int playerNumber, T score) {
        setPlayerScore(playerNumber == 0 ? 1 : 0, score);
    }
}
