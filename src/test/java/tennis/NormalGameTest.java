package tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NormalGameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player("testPlayer1");
        player2 = new Player("testPlayer2");
        game = new NormalGame(player1, player2);
    }

    @Test
    void normalGame_newInstance_scoresSetToZero() {
        player1.scorePoint();
        player2.scorePoint();
        game = new NormalGame(player1, player2);
        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
    }

    @ParameterizedTest
    @CsvSource({"4, 2", "7, 5", "8, 1"})
    void playerWinsGame_twoPointsAheadAndScoreGreaterThanFour_willReturnTrue(int player1Points, int player2Points) {
        for (int i = 0; i < player1Points; i++) {
            player1.scorePoint();
        }
        for (int i = 0; i < player2Points; i++) {
            player2.scorePoint();
        }
        assertTrue(game.playerWinsGame(player1, player2));
    }

    @ParameterizedTest
    @CsvSource({"2, 4", "7, 6", "3, 1"})
    void playerWinsGame_lessThanTowPointsAheadOrScoreLessThanFour_willReturnFalse(int player1Points, int player2Points) {
        for (int i = 0; i < player1Points; i++) {
            player1.scorePoint();
        }
        for (int i = 0; i < player2Points; i++) {
            player2.scorePoint();
        }
        assertFalse(game.playerWinsGame(player1, player2));
    }

    @Test
    void getScore_playerHasWon_returnsWinMessage() {
        for (int i = 0; i < 4; i++) {
            player1.scorePoint();
        }
        assertTrue(game.playerWinsGame(player1, player2));
        assertEquals("Won by testPlayer1", game.getGameScore());
    }

    @Test
    void getScore_onePointAheadAndPointsAtLeastThree_returnsAdvantageMessage() {
        for (int i = 0; i < 4; i++) {
            player1.scorePoint();
        }
        for (int i = 0; i < 3; i++) {
            player2.scorePoint();
        }
        assertEquals("Advantage testPlayer1", game.getGameScore());
    }

    @Test
    void getScore_pointsEqualAndAtLeastThree_returnsDeuceMessage() {
        for (int i = 0; i < 3; i++) {
            player1.scorePoint();
        }
        for (int i = 0; i < 3; i++) {
            player2.scorePoint();
        }
        assertEquals("Deuce", game.getGameScore());
    }

    @ParameterizedTest
    @CsvSource({"2,2,30-30", "3,0,40-0", "1,3,15-40"})
    void getScore_neitherWonAndAtLeastOneBelowThree_returnsNumericalScores(int player1Points, int player2Points,
                                                                           String expectedScoreMessage) {
        for (int i = 0; i < player1Points; i++) {
            player1.scorePoint();
        }
        for (int i = 0; i < player2Points; i++) {
            player2.scorePoint();
        }
        assertEquals(expectedScoreMessage, game.getGameScore());
    }

}