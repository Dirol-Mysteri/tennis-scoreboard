package org.example.tennisscoreboard.models;

import org.example.tennisscoreboard.services.ScoreService.MatchScore;

import java.util.Objects;
import java.util.UUID;

public class MatchScoreModel {
    private UUID matchId;
    private Player playerOne;
    private Player playerTwo;
    private MatchScore matchScore;

    public MatchScoreModel(Player playerOne, Player playerTwo) {
        this.matchId = UUID.randomUUID();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.matchScore = new MatchScore();
    }

    public UUID getMatchId() {
        return matchId;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public MatchScore getMatchScore() {
        return matchScore;
    }

    public void updateScore(MatchScore matchScore) {
        if (matchScore == null) {
            throw new NullPointerException("Score cannot be null");
        }

        this.matchScore = matchScore;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MatchScoreModel that = (MatchScoreModel) o;
        return Objects.equals(matchId, that.matchId) && Objects.equals(playerOne, that.playerOne) && Objects.equals(playerTwo, that.playerTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, playerOne, playerTwo);
    }


    @Override
    public String toString() {
        return "MatchScoreModel_new{" +
                "matchId=" + matchId +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", matchScore=" + matchScore +
                '}';
    }
}
