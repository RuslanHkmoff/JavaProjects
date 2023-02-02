package game;

public class Match {
    private final Game game;
    private final int winsNeeded;
    private Board board;
    private final int m, n, k;
    private int result;
    private int first = 1;
    private int points1 = 0, points2 = 0;


    public Match(Game game, int winsNeeded, int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.game = game;
        this.winsNeeded = winsNeeded;
        board = new mnkBoard(m, n, k);
    }

    void createMatch(Match match) {
        do {
            result = game.play(board, first);
            if (result == 1) {
                points1++;
            }
            if (result == 2) {
                points2++;
            }
            first = 3 - first;
            System.out.println("points1: " + points1);
            System.out.println("points2: " + points2);
            board.clean();
        } while (points1 != winsNeeded && points2 != winsNeeded);
    }
}
