package org.example.tennisscoreboard.services.ScoreService;

public class SetScore extends Score<Integer> {
    private GameScore gameScore;

    public SetScore() {
        super();
        this.gameScore = new GameScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    State pointWon(int playerNumber) {
        State gameState = this.gameScore.pointWon(playerNumber);
        if (gameState == State.PLAYER_ONE_WON || gameState == State.PLAYER_TWO_WON) {
            return gameWon(playerNumber);
        }
        return State.ONGOING;
    }

    private State gameWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        this.gameScore = new GameScore();
        return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
    }

    public GameScore getGameScore() {
        return gameScore;
    }
}
