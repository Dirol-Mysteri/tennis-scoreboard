package org.example.tennisscoreboard.services.ScoreService;

import org.example.tennisscoreboard.models.PlayerScore;

public class MatchScore extends Score<PlayerScore> {

    private SetScore setScore;

    public MatchScore() {
        super();
        this.setScore = new SetScore();
    }

    public MatchScore(MatchScore matchScore) {
        setPlayerScore(0, matchScore.getPlayerScore(0));
        setPlayerScore(0, matchScore.getPlayerScore(1));
    }

    @Override
    protected PlayerScore getZeroScore() {
        return new PlayerScore("0", 0, 0);
    }

    @Override
    public State pointWon(int playerNumber) {

        if (playerNumber != 0 && playerNumber != 1) {
            throw new IllegalArgumentException("Player number must be 0 or 1");
        }
        State state = this.setScore.pointWon(playerNumber);
        if (state == State.ONGOING) {
            setPlayerScore(0, createPlayerScore(0));
            setPlayerScore(1, createPlayerScore(1));
            return State.ONGOING;
        } else {
            return setWon(playerNumber);
        }
    }

    private State setWon(int playerNumber) {
        int playerSetScore = this.setScore.getPlayerScore(playerNumber);

        if (playerSetScore == 2) {
            setPlayerScore(0, createPlayerScore(0));
            setPlayerScore(1, createPlayerScore(1));
            return State.PLAYER_ONE_WON;
        } else {
            setPlayerScore(0, createPlayerScore(0));
            setPlayerScore(1, createPlayerScore(1));
            return State.ONGOING;
        }
    }

    private PlayerScore createPlayerScore(int playerNumber) {
        int playerSets = this.setScore.getPlayerScore(playerNumber);
        int playerGames = this.setScore.getGameScore().getPlayerScore(playerNumber);
        String playerPoints = this.setScore.getGameScore().getPointScore().getPlayerScore(playerNumber);

        return new PlayerScore(playerPoints, playerGames, playerSets);
    }

}
