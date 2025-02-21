package service;

import org.example.tennisscoreboard.services.ScoreService.GameScore;
import org.example.tennisscoreboard.services.ScoreService.RegularGamePlayerPoints;
import org.example.tennisscoreboard.services.ScoreService.RegularPointScore;
import org.example.tennisscoreboard.services.ScoreService.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MatchScoreCalculationServiceTest {
//    private GameScore gameScore;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDeuceMode() {
//        Given
        RegularPointScore pointScore = new RegularPointScore();
        pointScore.setPlayerScore(0, RegularGamePlayerPoints.FORTY.getPointCode());
        pointScore.setPlayerScore(1, RegularGamePlayerPoints.FORTY.getPointCode());

//        When
        pointScore.pointWon(0);

//        Then
        assertThat(pointScore.getPlayerScore(0)).isEqualTo(RegularGamePlayerPoints.ADVANTAGE.getPointCode());

//        When
        State state = pointScore.pointWon(0);

//        Then
        assertThat(state).isEqualTo(State.PLAYER_ONE_WON);

    }

    @Test
    void testGameWin() {
//        Given
        RegularPointScore pointScore = new RegularPointScore();
        State state = null;
//        When
        for (int i = 0; i < 4; i++) {
            state = pointScore.pointWon(0);
        }

//        Then
        assertThat(state).isEqualTo(State.PLAYER_ONE_WON);
    }

    @Test
    void testTiebreak() {
//        Given
        GameScore gameScore = new GameScore();
        gameScore.setPlayerScore(0, 5);
        gameScore.setPlayerScore(1, 6);
        State state = null;

//        When
        for (int i = 0; i < 5; i++) {
            state = gameScore.pointWon(0);
        }

//        Then
        assertThat(state).isEqualTo(State.ONGOING);
        assertThat(gameScore.getPointScore().getPlayerScore(0)).isEqualTo("1");
    }
}
