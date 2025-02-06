package org.example.tennisscoreboard.models;

import java.util.Objects;
import java.util.UUID;

public class MatchScoreModel {
    private UUID matchId;
    private Long playerOneId;
    private Long playerTwoId;
    private Score score;

    public MatchScoreModel(Long playerOneId, Long playerTwoId) {
        this.matchId = UUID.randomUUID();
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.score = new Score();
    }

    public MatchScoreModel(Long playerOneId, Long playerTwoId, int scorePlayerOne, int scorePlayerTwo) {
        this.matchId = UUID.randomUUID();
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.score = new Score(scorePlayerOne, scorePlayerTwo,0,0,0,0);
    }

    public UUID getMatchId() {
        return matchId;
    }

    public Long getPlayerOneId() {
        return playerOneId;
    }

    public Long getPlayerTwoId() {
        return playerTwoId;
    }

    public Score getScore() {
        return score;
    }

    public void updateScore(Score score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MatchScoreModel that = (MatchScoreModel) o;
        return Objects.equals(matchId, that.matchId) && Objects.equals(playerOneId, that.playerOneId) && Objects.equals(playerTwoId, that.playerTwoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, playerOneId, playerTwoId);
    }

    @Override
    public String toString() {
        return "MatchScoreModel{" +
                "matchId=" + matchId +
                ", playerOneId=" + playerOneId +
                ", playerTwoId=" + playerTwoId +
                ", score=" + score +
                '}';
    }
}
