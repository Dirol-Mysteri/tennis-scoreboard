package org.example.tennisscoreboard.models;


public class PlayerScore {
    private String playerPoints;
    private int playerGames;
    private int playerSets;

    public PlayerScore(String playerOnePoints, int playerOneGames, int playerOneSets) {
        this.playerPoints = playerOnePoints;
        this.playerGames = playerOneGames;
        this.playerSets = playerOneSets;
    }

    public PlayerScore() {
    }

    public String getPlayerPoints() {
        return playerPoints;
    }

    public int getPlayerGames() {
        return playerGames;
    }

    public int getPlayerSets() {
        return playerSets;
    }
}
