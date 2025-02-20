package org.example.tennisscoreboard.services.ScoreService;

public class TiebrakePointScore extends PointScore {
    @Override
    protected String getZeroScore() {
        return "0";
    }

    @Override
    State pointWon(int playerNumber) {
        int playerScore = Integer.parseInt(getPlayerScore(playerNumber)) + 1;
        setPlayerScore(playerNumber, Integer.toString(playerScore));
        int opponentScore = Integer.parseInt(getOppositePlayerScore(playerNumber));

        if (playerScore >= 7 && playerScore - opponentScore >= 2) {
            // Player wins the tiebreak with a 2-point advantage
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        } else {
            return State.ONGOING;
        }
    }
}
