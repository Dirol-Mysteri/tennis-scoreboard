package org.example.tennisscoreboard.services.ScoreService;

public class RegularPointScore extends PointScore {
    @Override
    protected String getZeroScore() {
        return "0";
    }

    @Override
    State pointWon(int playerNumber) {
        RegularGamePlayerPoints playerScore = RegularGamePlayerPoints.findByPointCode(getPlayerScore(playerNumber));
        // 0:X, 15:X or 30:X
        if (playerScore.ordinal() <= RegularGamePlayerPoints.THIRTY.ordinal()) {
            setPlayerScore(playerNumber, playerScore.next().getPointCode());
        } else if (playerScore == RegularGamePlayerPoints.FORTY) {
            // 40:X
            RegularGamePlayerPoints oppositePlayerScore = RegularGamePlayerPoints.findByPointCode(getOppositePlayerScore(playerNumber));
            if (oppositePlayerScore == RegularGamePlayerPoints.ADVANTAGE) {
                //  40:AD
                setOppositePlayerScore(playerNumber, RegularGamePlayerPoints.FORTY.getPointCode());
            } else if (oppositePlayerScore == RegularGamePlayerPoints.FORTY) {
                // 40:40
                setPlayerScore(playerNumber, RegularGamePlayerPoints.ADVANTAGE.getPointCode());
            } else {
                // 40:0, 40:15, 40:30 -> Wins the game
                return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
            }
        } else if (playerScore == RegularGamePlayerPoints.ADVANTAGE) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        } else {
            throw new IllegalStateException("Cannot call pointWon() on ADVANTAGE");
        }
        return State.ONGOING;
    }
}
