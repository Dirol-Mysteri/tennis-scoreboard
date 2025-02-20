package service;

import static org.assertj.core.api.Assertions.*;

import org.example.tennisscoreboard.enums.Players;
import org.example.tennisscoreboard.models.Score;
import org.example.tennisscoreboard.services.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService matchScoreCalculationService;

    @BeforeEach
    void setUp() {
        matchScoreCalculationService = new MatchScoreCalculationService();
    }

//    @Test
//    void testDeuceMode() {
////        Given
//        Score score = new Score(40, 40, 0, 0, 0, 0);
//
////        When
//        Score newScore = matchScoreCalculationService.addPointToPlayer(Players.PLAYER_ONE, score);
//
////        Then
//        assertThat(newScore)
//                .extracting(Score::getPlayerOneSets, Score::getPlayerTwoSets)
//                .containsExactly(0, 0);
//    }
//
//    @Test
//    void TestGameWin() {
////        Given
//        Score score = new Score(40, 0, 0, 0, 0, 0);
//
////        When
//        Score newScore = matchScoreCalculationService.addPointToPlayer(Players.PLAYER_ONE, score);
//
////        Then
//        assertThat(newScore)
//                .extracting(Score::getPlayerOneGames, Score::getPlayerTwoGames)
//                .containsExactly(1, 0);
//    }
//
//    @Test
//    void TestTiebreakStart() {
////        Given
//        Score score = new Score(0, 0, 6, 6, 0, 0);
//
////        When
//        Score newScore = matchScoreCalculationService.addPointToPlayer(Players.PLAYER_ONE, score);
//
////        Then
//        assertThat(newScore)
//                .extracting(Score::getPlayerOnePoints, Score::getPlayerTwoPoints)
//                .containsExactly(1, 0);
//    }
//
//    @Test
//    void TestTiebreakEnd() {
////        Given
//        Score score = new Score(7, 6, 6, 6, 0, 0);
//
////        When
//        Score newScore = matchScoreCalculationService.addPointToPlayer(Players.PLAYER_ONE, score);
//
////        Then
//        assertThat(newScore)
//                .extracting(Score::getPlayerOneSets, Score::getPlayerTwoSets, Score::getPlayerTwoGames, Score::getPlayerOneGames, Score::getPlayerOnePoints, Score::getPlayerTwoPoints)
//                .containsExactly(1, 0, 0, 0, 0, 0);
//    }

}
