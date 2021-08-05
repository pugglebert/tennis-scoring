package tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TieBreakGameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player("testPlayer1");
        player2 = new Player("testPlayer2");
        game = new TieBreakGame(player1, player2);
    }

    @Test
    void tieBreakGame_newInstance_scoresSetToZero() {
        player1.scorePoint();
        player2.scorePoint();
        game = new TieBreakGame(player1, player2);
        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
    }

    @ParameterizedTest
    @CsvSource({"7, 5", "7, 0", "8, 6"})
    void playerWinsGame_twoPointsAheadAndScoreGreaterThanSeven_willReturnTrue(int player1Points, int player2Points) {
        for (int i = 0; i < player1Points; i++) {
            player1.scorePoint();
        }
        for (int i = 0; i < player2Points; i++) {
            player2.scorePoint();
        }
        assertTrue(game.playerWinsGame(player1, player2));
    }

    @ParameterizedTest
    @CsvSource({"5, 7", "9, 8", "6, 0"})
    void playerWinsGame_lessThanTowPointsAheadOrScoreLessThanSeven_willReturnFalse(int player1Points, int player2Points) {
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
        for (int i = 0; i < 7; i++) {
            player1.scorePoint();
        }
        assertTrue(game.playerWinsGame(player1, player2));
        assertEquals("Won by testPlayer1", game.getGameScore());
    }

    @ParameterizedTest
    @CsvSource({"2,2,2-2", "13,12,13-12", "0,5,0-5"})
    void getScore_neitherWon_returnsNumericalScores(int player1Points, int player2Points,
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