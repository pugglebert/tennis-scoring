package tennis;

public class Main {

    public static void main(String[] args) {
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

    }
}
