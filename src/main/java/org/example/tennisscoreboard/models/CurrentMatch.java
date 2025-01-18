package org.example.tennisscoreboard.models;

import java.util.UUID;

public class CurrentMatch {
    private UUID matchId;
    private Long playerOneId;
    private Long playerTwoId;
    private Score score;

    public CurrentMatch(Long playerOneId, Long playerTwoId) {
        this.matchId = UUID.randomUUID();
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.score = new Score(0, 0);
    }

    public CurrentMatch(Long playerOneId, Long playerTwoId, int scorePlayerOne, int scorePlayerTwo) {
        this.matchId = UUID.randomUUID();
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.score = new Score(scorePlayerOne, scorePlayerTwo);
    }

    public UUID getCurrentMatchId() {
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

    public void updateScore(int playerOneScore, int playerTwoScore) {
        score.setPlayerOneScore(playerOneScore);
        score.setPlayerTwoScore(playerTwoScore);
    }
}
