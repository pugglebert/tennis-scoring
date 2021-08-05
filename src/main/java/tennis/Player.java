package tennis;

/**
 * A player within a tennis match.
 */
public class Player {

    private static final int[] POINT_SCORES = new int[] {0, 15, 30, 40};

    private int points = 0;
    private int wins = 0;
    private final String name;

    /**
     * Initialize the player by setting their name to the given string.
     * @param name The name used to refer to the player.
     */
    public Player(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }

    /**
     * Compute the numerical score of the player based on their current number of points. This method should only be
     * called if the player's points are less than four, as otherwise they do not have a numerical score.
     * @return The numerical score of this player, or null if their number of points is greater than three.
     */
    public Integer getScore() {
        if (points < POINT_SCORES.length) {
            return POINT_SCORES[points];
        }
        return null;
    }

    /**
     * Increment the number of points for this player by one.
     */
    public void scorePoint() {
        this.points += 1;
    }

    /**
     * Increment the number of wins for this player by one.
     */
    public void winGame() {
        this.wins += 1;
    }

    /**
     * Reset the number of points scored by this player to zero.
     */
    public void resetPoints() {
        points = 0;
    }

}
