package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            try {
                out.println(cell + "'s move");
                out.println("Enter row and column");
                int x = Integer.parseInt(in.next());
                int y = Integer.parseInt(in.next());;
                if (!position.isValid(new Move(x, y, cell))){
                    throw new Exception();
                }
                final Move move = new Move(x, y, cell);
                final int row = move.getRow();
                final int column = move.getColumn();
                return move;
            } catch (Exception e) {
                in.nextLine();
                out.println("Invalid input, try again");

            }
        }
    }
}
