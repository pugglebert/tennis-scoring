package tennis;

public class Main {

    public static void main(String[] args) {
        runScenario1();
        runScenario2();
        runScenario3();
    }

    /**
     * Create a match scenario to demonstrate the scoring for a normal game.
     */
    public static void runScenario1() {
        System.out.println("Scenario 1: Normal game scoring");
        Match match = new Match("player 1", "player 2");
        match.pointWonBy("player 1");
        match.pointWonBy("player 2");
        // this will return "0-0, 15-15"
        System.out.println(match.score());

        match.pointWonBy("player 1");
        match.pointWonBy("player 1");
        // this will return "0-0, 40-15"
        System.out.println(match.score());

        match.pointWonBy("player 2");
        match.pointWonBy("player 2");
        // this will return "0-0, Deuce"
        System.out.println(match.score());

        match.pointWonBy("player 1");
        // this will return "0-0, Advantage player 1"
        System.out.println(match.score());

        match.pointWonBy("player 1");
        // this will return "1-0"
        System.out.println(match.score());

        System.out.println();
    }

    /**
     * Create a match scenario to demonstrate the scoring for a tie break game.
     */
    public static void runScenario2() {
        System.out.println("Scenario 2: Tie break game scoring");
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                match.pointWonBy("player 1");
            }
            for (int j = 0; j < 4; j++) {
                match.pointWonBy("player 2");
            }
        }
        // this will return "6-6"
        System.out.println(match.score());
        match.pointWonBy("player 1");
        match.pointWonBy("player 2");
        // this will return "6-6, 1-1"
        System.out.println(match.score());

        for (int i = 0; i < 4; i++) {
            match.pointWonBy("player 1");
        }
        // this will return "6-6, 5-1"
        System.out.println(match.score());

        for (int i = 0; i < 4; i++) {
            match.pointWonBy("player 2");
        }
        // this will return "6-6, 5-5"
        System.out.println(match.score());

        match.pointWonBy("player 1");
        match.pointWonBy("player 2");
        match.pointWonBy("player 1");
        // this will return "6-6, 7-6"
        System.out.println(match.score());

        match.pointWonBy("player 1");
        // this will return "Match won by player 1"
        System.out.println(match.score());

        System.out.println();
    }

    /**
     * Create a match scenario to demonstrate the scoring for a match that ends without a tie break.
     */
    public static void runScenario3() {
        System.out.println("Scenario 3: Match scoring without a tie break game");
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                match.pointWonBy("player 1");
            }
            System.out.println(match.score());
        }
        System.out.println();
    }
}
