package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.enums.Players;
import org.example.tennisscoreboard.models.MatchScoreModel;
import org.example.tennisscoreboard.models.Score;

import java.util.Objects;

public class MatchScoreCalculationService {

    public void addScoreToTheCurrentMatchWinner(MatchScoreModel matchScoreModel, Long winnerOfThePointID) {
        Score currentScore = matchScoreModel.getScore();
        Players player = Objects.equals(winnerOfThePointID, matchScoreModel.getPlayerOneId()) ? Players.PLAYER_ONE : Players.PLAYER_TWO;
        Score newScore = addPointToPlayer(player, currentScore);
        matchScoreModel.updateScore(newScore);
    }

    public Score addPointToPlayer(Players player, Score score) {

        PlayerScore playerOneScore = new PlayerScore(score.getPlayerOnePoints(), score.getPlayerOneGames(), score.getPlayerOneSets());
        PlayerScore playerTwoScore = new PlayerScore(score.getPlayerTwoPoints(), score.getPlayerTwoGames(), score.getPlayerTwoSets());

        if (isTiebreak(playerOneScore.games, playerTwoScore.games)) {
            handleTiebreak(player, playerOneScore, playerTwoScore);
        } else {
            handleNormalGame(player, playerOneScore, playerTwoScore);
        }
        return new Score(playerOneScore.points, playerTwoScore.points, playerOneScore.games, playerTwoScore.games, playerOneScore.sets, playerTwoScore.sets);
    }

    private void handleTiebreak(Players player, PlayerScore playerOne, PlayerScore playerTwo) {
        if (player == Players.PLAYER_ONE) {
            playerOne.points++;
            if (playerOne.points >= 7 && (playerOne.points - playerTwo.points) >= 2) {
                playerOne.points = 0;
                playerTwo.points = 0;
                playerOne.games = 0;
                playerTwo.games = 0;
                playerOne.sets++;
            }
        } else {
            playerTwo.points++;
            if (playerTwo.points >= 7 && (playerTwo.points - playerOne.points) >= 2) {
                playerOne.points = 0;
                playerTwo.points = 0;
                playerOne.games = 0;
                playerTwo.games = 0;
                playerTwo.sets++;
            }
        }
    }

    private void handleNormalGame(Players player, PlayerScore playerOne, PlayerScore playerTwo) {
        if (player == Players.PLAYER_ONE) {
            playerOne.points = playerOne.points == 30 ? playerOne.points + 10 : playerOne.points + 15;

            if (playerOne.points >= 40 && (playerOne.points - playerTwo.points) >= 30) {
                playerOne.points = 0;
                playerTwo.points = 0;
                playerOne.games++;
                if (playerOne.games >= 6 && (playerOne.games - playerTwo.games) >= 2) {
                    playerOne.sets++;
                    playerOne.games = 0;
                    playerTwo.games = 0;
                }
            }
        } else {
            playerTwo.points = playerTwo.points == 30 ? playerTwo.points + 10 : playerTwo.points + 15;

            if (playerTwo.points >= 40 && (playerTwo.points - playerOne.points) >= 30) {
                playerOne.points = 0;
                playerTwo.points = 0;
                playerTwo.games++;
                if (playerTwo.games >= 6 && (playerTwo.games - playerOne.games) >= 2) {
                    playerTwo.sets++;
                    playerOne.games = 0;
                    playerTwo.games = 0;
                }
            }
        }
    }

    private boolean isMatchEnded(Score score) {
        return score.getPlayerOneSets() == 2 || score.getPlayerTwoSets() == 2;
    }

    private boolean isTiebreak(int games1, int games2) {
        return games1 == 6 && games1 == games2;
    }

    class PlayerScore {
        int points;
        int games;
        int sets;

        public PlayerScore(int points, int games, int sets) {
            this.points = points;
            this.games = games;
            this.sets = sets;
        }
    }
}
