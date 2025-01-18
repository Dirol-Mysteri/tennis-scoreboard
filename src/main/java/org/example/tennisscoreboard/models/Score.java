package org.example.tennisscoreboard.models;

public class Score {
    int playerOneScore;
    int playerTwoScore;

    public Score(int playerOneScore, int playerTwoScore) {
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "playerOneScore=" + playerOneScore +
                ", playerTwoScore=" + playerTwoScore +
                '}';
    }
}
