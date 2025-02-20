package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.models.MatchScoreModel;
import org.example.tennisscoreboard.models.Player;

public class MatchService {
    private final OngoingMatchesService ongoingMatchesService;

    public MatchService() {
        this.ongoingMatchesService = OngoingMatchesService.getInstance();
    }

    public MatchScoreModel addNewMatch(String playerOneName, String playerTwoName) throws Exception {
        Player playerOne;
        Player playerTwo;

        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);


        MatchScoreModel matchScoreModel = new MatchScoreModel(playerOne, playerTwo);

        ongoingMatchesService.addMatch(matchScoreModel);

        return matchScoreModel;
    }
}