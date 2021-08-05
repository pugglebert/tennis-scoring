package tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private Match match;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        match = new Match("testPlayer1", "testPlayer2");
        Player[] players = match.getPlayers();
        player1 = players[0];
        player2 = players[1];
    }

    @Test
    void pointWonBy_matchHasNotBeenWon_playerPointsIncremented() {
        assertFalse(match.hasBeenWon());
        assertEquals(0, player1.getPoints());
        match.pointWonBy(player1.getName());
        assertEquals(1, player1.getPoints());
    }

    @Test
    void pointWonBy_matchHasNotBeenWonAndPlayerOnePointOffWiningGame_playerWinsIncremented() {
        for (int i = 0; i < 3; i++) {
            match.pointWonBy(player1.getName());
        }
        assertFalse(match.hasBeenWon());
        assertEquals(3, player1.getPoints());
        assertEquals(0, player1.getWins());
        match.pointWonBy(player1.getName());
        assertEquals(1, player1.getWins());
    }

    @Test
    void pointWonBy_matchHasNotBeenWonAndPlayerMoreThanOnePointOffWiningGame_playerWinsIncremented() {
        assertFalse(match.hasBeenWon());
        assertEquals(0, player1.getWins());
        assertEquals(0, player1.getPoints());
        match.pointWonBy(player1.getName());
        assertEquals(0, player1.getWins());
    }


    @Test
    void pointWonBy_matchHasBeenWon_playerPointsNotIncremented() {
        for (int i = 0; i < 4 * 6; i++) {
            match.pointWonBy(player1.getName());
        }
        assertTrue(match.hasBeenWon());
        assertEquals(0, player1.getPoints());
        match.pointWonBy(player1.getName());
        assertEquals(0, player1.getPoints());
    }

    @Test
    void pointWonBy_matchHasBeenWon_playerWinsNotIncremented() {
        for (int i = 0; i < 4 * 6; i++) {
            match.pointWonBy(player1.getName());
        }
        assertTrue(match.hasBeenWon());
        assertEquals(6, player1.getWins());
        for (int i = 0; i < 4; i++) {
            match.pointWonBy(player1.getName());
        }
        assertEquals(6, player1.getWins());
    }

    @Test
    void pointWonBy_pointWinsGame_bothPlayerPointsReset() {
        for (int i = 0; i < 3; i++) {
            match.pointWonBy(player1.getName());
        }
        for (int i = 0; i < 2; i++) {
            match.pointWonBy(player2.getName());
        }
        assertEquals(3, player1.getPoints());
        assertEquals(2, player2.getPoints());
        match.pointWonBy(player1.getName());
        assertEquals(0, player1.getPoints());
        assertEquals(0, player2.getPoints());
    }

    @ParameterizedTest
    @CsvSource({"0,0,0-0", "5,5,5-5", "5,3,5-3", "1,5,1-5"})
    void score_matchNotWonAndGameNotStarted_returnsMatchScore(int player1Wins, int player2Wins, String expectedMessage) {
        for (int i = 0; i < player1Wins * 4; i++) {
            match.pointWonBy(player1.getName());
        }
        for (int i = 0; i < player2Wins * 4; i++) {
            match.pointWonBy(player2.getName());
        }
        assertEquals(expectedMessage, match.score());
    }

    @ParameterizedTest
    @CsvSource({"0,0,0-0", "5,5,5-5", "5,3,5-3", "1,5,1-5"})
    void score_matchNotWonAndGameStarted_returnsMatchAndGameScore(int player1Wins, int player2Wins, String gameScore) {
        for (int i = 0; i < player1Wins * 4; i++) {
            match.pointWonBy(player1.getName());
        }
        for (int i = 0; i < player2Wins * 4; i++) {
            match.pointWonBy(player2.getName());
        }
        match.pointWonBy(player1.getName());
        assertEquals(String.format("%s, 15-0", gameScore), match.score());
    }

    @Test
    void score_matchWonWithoutTieBreaker_returnsWinMessage() {
        for (int i = 0; i < 6 * 4; i++) {
            match.pointWonBy(player1.getName());
        }
        assertEquals("6-0: Match won by testPlayer1", match.score());
    }

    @Test
    void score_matchWonWithTieBreaker_returnsWinMessage() {
        for (int i = 0; i < 5 * 4; i++) {
            match.pointWonBy(player1.getName());
        }
        for (int i = 0; i < 5 * 4; i++) {
            match.pointWonBy(player2.getName());
        }
        for (int i = 0; i < 4; i++) {
            match.pointWonBy(player1.getName());
        }
        for (int i = 0; i < 4 + 7; i++) {
            match.pointWonBy(player2.getName());
        }
        assertEquals("6-7: Match won by testPlayer2", match.score());
    }


}