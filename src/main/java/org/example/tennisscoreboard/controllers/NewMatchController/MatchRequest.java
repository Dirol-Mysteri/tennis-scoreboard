package org.example.tennisscoreboard.controllers.NewMatchController;

public class MatchRequest {
    private String playerOne;
    private String playerTwo;

    public MatchRequest() {
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }
}
