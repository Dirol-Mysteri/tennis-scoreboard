package org.example.tennisscoreboard.models;

public class Score {
    private int playerOnePoints;
    private int playerOneGames;
    private int playerOneSets;

    private int playerTwoPoints;
    private int playerTwoGames;
    private int playerTwoSets;

    public Score() {
        this(0, 0, 0, 0, 0, 0);
    }

    public Score(int playerOnePoints, int playerTwoPoints, int playerOneGames, int playerTwoGames, int playerOneSets, int playerTwoSets) {
        this.playerOnePoints = playerOnePoints;
        this.playerTwoPoints = playerTwoPoints;
        this.playerOneGames = playerOneGames;
        this.playerTwoGames = playerTwoGames;
        this.playerOneSets = playerOneSets;
        this.playerTwoSets = playerTwoSets;
    }

    public int getPlayerOnePoints() {
        return playerOnePoints;
    }

    public int getPlayerOneGames() {
        return playerOneGames;
    }

    public int getPlayerOneSets() {
        return playerOneSets;
    }

    public int getPlayerTwoPoints() {
        return playerTwoPoints;
    }

    public int getPlayerTwoGames() {
        return playerTwoGames;
    }

    public int getPlayerTwoSets() {
        return playerTwoSets;
    }

//    public void addPointToPlayer(Players player) {
//        if (player == Players.PLAYER_ONE) {
//            playerOnePoints += 15;
//
//            if (playerOnePoints >= 40 && (playerOnePoints - playerTwoPoints) >= 30) {
//                playerOnePoints = 0;
//                playerTwoPoints = 0;
//                playerOneGames++;
//                if (playerOneGames >= 6 && (playerOneGames - playerTwoGames) >= 2) {
//                    playerOneSets ++;
//                    if (playerOneSets >= 2 && (playerOneSets - playerTwoSets) >= 2) {}
//                }
//            }
//
//        } else if (player == Players.PLAYER_TWO) {
//            playerTwoPoints += 15;
//        }
//    }

    @Override
    public String toString() {
        return "Score{" +
                "playerOnePoints=" + playerOnePoints +
                ", playerOneGames=" + playerOneGames +
                ", playerOneSets=" + playerOneSets +
                ", playerTwoPoints=" + playerTwoPoints +
                ", playerTwoGames=" + playerTwoGames +
                ", playerTwoSets=" + playerTwoSets +
                '}';
    }
}
