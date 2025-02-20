package org.example.tennisscoreboard.services.ScoreService;

public class GameScore extends Score<Integer> {

    private PointScore pointScore;

    public GameScore() {
        super();
        this.pointScore = new RegularPointScore();
    }

    public PointScore getPointScore() {
        return pointScore;
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State pointWon(int playerNumber) {
        State state = pointScore.pointWon(playerNumber);

        if (state == State.PLAYER_ONE_WON || state == State.PLAYER_TWO_WON) {
            return gameWon(playerNumber);
        } else {
            return State.ONGOING;
        }
    }

    private State gameWon(int playerNumber) {
        if (isTiebrake()) {
            this.pointScore = new RegularPointScore();
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }

        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        int playerGameScore = getPlayerScore(playerNumber);
        int oppositePlayerGameScore = getOppositePlayerScore(playerNumber);

        if (isTiebrake()) {
            this.pointScore = new TiebrakePointScore();
            return State.ONGOING;
        }

        if (playerGameScore >= 6 && playerGameScore - oppositePlayerGameScore >= 2) {
            this.pointScore = new RegularPointScore();
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }

        if  (oppositePlayerGameScore >= 6 && oppositePlayerGameScore - playerGameScore >= 2) {
            this.pointScore = new RegularPointScore();
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }
        this.pointScore = new RegularPointScore();
        return State.ONGOING;
    }

    private boolean isTiebrake() {
        return getPlayerScore(0) == 6 && getPlayerScore(1) == 6;
    }
}
