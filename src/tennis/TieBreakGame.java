package tennis;

/**
 * A game used to break a tie between two players in a tennis match.
 */
public class TieBreakGame extends Game {

    /**
     * Initialize the game by setting the scores of both players to zero.
     * @param player1 The first player.
     * @param player2 The second player.
     */
    public TieBreakGame(Player player1, Player player2) {
        player1.resetPoints();
        player2.resetPoints();
        players = new Player[] {player1, player2};
    }

    @Override
    public boolean playerWinsGame(Player player, Player opponent) {
        return (player.getPoints() >=7 &&
                player.getPoints() >= opponent.getPoints() + 2);
    }

    @Override
    public String getGameScore() {
        for (int i = 0; i < players.length; i++) {
            if (playerWinsGame(players[i], players[(i + 1) % 2])) {
                return String.format("Won by %s", players[i].getName());
            }
        }
        return String.format("%d-%d", players[0].getPoints(), players[1].getPoints());

    }
}
