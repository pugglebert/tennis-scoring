package tennis;

/**
 * A game within a tennis match that is not a tie breaker.
 */
public class NormalGame extends Game {

    /**
     * Initialize the game by setting the scores of both players to zero.
     * @param player1 The first player.
     * @param player2 The second player.
     */
    public NormalGame(Player player1, Player player2) {
        player1.resetPoints();
        player2.resetPoints();
        players = new Player[] {player1, player2};
    }

    @Override
    public boolean playerWinsGame(Player player, Player opponent) {
        return (player.getPoints() >=4 &&
                player.getPoints() >= opponent.getPoints() + 2);
    }

    /**
     * @return True if both players have a score of at least three and the player's score is a point higher than their
     * opponent's, false otherwise.
     */
    private boolean playerHasAdvantage(Player player, Player opponent) {
        return (player.getPoints() >= 3 &&
                opponent.getPoints() >= 3 &&
                player.getPoints() > opponent.getPoints());
    }

    /**
     * @return True if both players have a score of at least three and the scores are tied, false otherwise.
     */
    private boolean isDeuce() {
        return (players[0].getPoints() >= 3 &&
                players[1].getPoints() >= 3 &&
                players[0].getPoints() == players[1].getPoints());
    }

    @Override
    public String getGameScore() {
        for (int i = 0; i < players.length; i++) {
            if (playerWinsGame(players[i], players[(i + 1) % 2])) {
                return String.format("Won by %s", players[i].getName());
            }
            if (playerHasAdvantage(players[i], players[(i + 1) % 2])) {
                return String.format("Advantage %s", players[i].getName());
            }
        }
        if (isDeuce()) {
            return "Deuce";
        }
        return String.format("%d-%d", players[0].getScore(), players[1].getScore());
    }

}
