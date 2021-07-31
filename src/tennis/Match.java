package tennis;

/**
 * Class used to compute the score of two players in a tennis match.
 */
public class Match {

    private final Player[] players;
    private Game currentGame;
    private boolean hasBeenWon = false;

    /**
     * Set up the match by initializing the two players and the first game of the match.
     * @param playerOneName The name of the first player.
     * @param playerTwoName The name of the second player.
     */
    public Match(String playerOneName, String playerTwoName) {
        players = new Player[2];
        players[0] = new Player(playerOneName);
        players[1] = new Player(playerTwoName);
        currentGame = new NormalGame(players[0], players[1]);
    }

    /**
     * If the match has not yet been won, the player with the given name scores a point. If the match has been won,
     * there is not change to the points.
     * @param playerName The name of the player who scored a point.
     */
    public void pointWonBy(String playerName) {
        if (hasBeenWon) {
            return;
        }
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                player.scorePoint();
            }
        }
        updateMatchScore();
    }

    /**
     * Check the number of games won by the given player and their opponent to see if the player has won the match.
     * A player wins the match if they have a score of at least six and at least two more than their opponent, or if
     * they are the first to win seven games.
     * @param player A player whose win condition will be checked to see if they have won.
     * @param opponent The opposing player, whose score the win condition is checked against.
     * @return True if the player has won the match, false otherwise.
     */
    private boolean playerWinsMatch(Player player, Player opponent) {
        return (player.getWins() >= 7 ||
                (player.getWins() >= 6 &&
                        player.getWins() >= opponent.getWins() + 2));
    }

    /**
     * Checks to see if either player has won the current game or the overall match. If a player has won the current game,
     * update their number of wins and starts a new game. If a player has won the overall match, freeze the scores at
     * their current value.
     */
    private void updateMatchScore() {
        for (int i = 0; i < players.length; i++) {
            if (currentGame.playerWinsGame(players[i], players[(i + 1) % 2])) {
                players[i].winGame();
                if (players[0].getWins() == 6 && players[1].getWins() == 6) {
                    currentGame = new TieBreakGame(players[0], players[1]);
                } else {
                    currentGame = new NormalGame(players[0], players[1]);
                }
                break;
            }
            if (playerWinsMatch(players[i], players[(i + 1) % 2])) {
                hasBeenWon = true;
            }
        }
    }

    /**
     * Return a string with the score of the match followed by the score of the current game if it has been started.
     * The string will give the winner's name if the game has been won.
     * @return A string representing the overall score of this game.
     */
    public String score() {
        for (int i = 0; i < players.length; i++) {
            if (playerWinsMatch(players[i], players[(i + 1) % 2])) {
                return String.format("Match won by %s", players[i].getName());
            }
        }
        String matchScore = String.format("%d-%d", players[0].getWins(), players[1].getWins());
        if (currentGame.hasStarted()) {
            String gameScore = currentGame.getGameScore();
            return String.format("%s, %s", matchScore, gameScore);
        }
        return matchScore;
    }


}
