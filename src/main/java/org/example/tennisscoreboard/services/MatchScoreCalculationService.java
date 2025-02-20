package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.models.MatchScoreModel;
import org.example.tennisscoreboard.services.ScoreService.MatchScore;
import org.example.tennisscoreboard.services.ScoreService.State;

public class MatchScoreCalculationService {

    private State matchState = State.ONGOING;

    public void addScoreToTheCurrentMatch(MatchScoreModel matchScoreModel, String winnerOfThePoint) {
        MatchScore matchScore = matchScoreModel.getMatchScore();
        int player = winnerOfThePoint.equals("playerOne") ? 0 : 1;
        this.matchState = matchScore.pointWon(player);
    }

    public State getMatchState() {
        return matchState;
    }
}
