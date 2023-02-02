package game;

//import static java.lang.System.out;

public class Game {
    private final boolean log;
    private  Player player1, player2;
private int first;
    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board, int first) {
        Player tmpPlayer;
        if (first == 2){
            tmpPlayer=player1;
            player1 = player2;
            player2 = tmpPlayer;
        }
        log("Position:\n" + board);
        while (true) {
            int result1;
            do {
                result1 = move(board, player1, first);
                if (result1 != -1 && result1 != -2) {
                    return result1;
                }

            } while (result1 == -2);
            int result2;
            do {
                result2 = move(board, player1, 3-first);
                if (result2 != -1 && result2 != -2) {
                    return result2;
                }
            } while (result2 == -2);
        }
    }

    private int move(final Board board, final Player player, final int no) {
        Result result;
        final Move move;
        try {
            move = player.move(board.getPosition(), board.getCell());
            result = board.makeMove(move);
            log("Player " + no + " move: " + move);
        } catch (Exception e){
            result = Result.LOSE;
        }
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won!");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose!");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw!");
            return 0;
        } else if (result == Result.AnotherOne) {
            log("You can make another one move!");
            return -2;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
