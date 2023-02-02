package game;

import java.util.Random;


public class RandomPlayer implements Player {
    private final Random random;
    private int m;
    private int n;

    public RandomPlayer(final Random random, int m, int n) {
        this.m = m;
        this.n = n;

        this.random = random;
    }

    public RandomPlayer(int m, int n)
    {

        this(new Random(), m, n);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(m);
            int c = random.nextInt(n);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
