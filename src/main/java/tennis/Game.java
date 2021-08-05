package tennis;

/**
 * A single game within a tennis match.
 */
public abstract class Game {

    protected Player[] players;

    /**
     * Return true if the player has won this game against their opponent, false otherwise.
     * @param player The player to check the win condition for in this game.
     * @param opponent The player's opponent whose score is used to check the win condition.
     * @return True if the given player has won this game.
     */
    public abstract boolean playerWinsGame(Player player, Player opponent);

    /**
     * @return A string representation of the score of the current game.
     */
    public abstract String getGameScore();

    /**
     * @return True if either player has scored a point so far, false otherwise.
     */
    public boolean hasStarted() {
        return players[0].getPoints() > 0 || players[1].getPoints() > 0;
    }

}
