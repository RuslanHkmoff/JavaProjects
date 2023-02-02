package game;

import java.util.Arrays;
import java.util.Map;

public class mnkBoard implements Board, Position {
    private int m;
    private int n;
    private int k;

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    int empty;

    public mnkBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        empty = m * n;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }


    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        int x = move.getRow(), y = move.getColumn();
        cells[x][y] = move.getValue();
        int deltaX = 0, deltaY = 0, tmpX = x, tmpY = y;
        int[] count = new int[8];
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    deltaX = -1;
                    deltaY = 0;
                    break;
                case 1:
                    deltaX = -1;
                    deltaY = 1;
                    break;
                case 2:
                    deltaX = 0;
                    deltaY = 1;
                    break;
                case 3:
                    deltaX = 1;
                    deltaY = 1;
                case 4:
                    deltaX = 1;
                    deltaY = 0;
                    break;
                case 5:
                    deltaX = 1;
                    deltaY = -1;
                    break;
                case 6:
                    deltaX = 0;
                    deltaY = -1;
                    break;
                case 7:
                    deltaX = -1;
                    deltaY = -1;
            }
            while (0 <= x && x < m && 0 <= y && y < n && cells[x][y] == turn) {
                count[i]++;
                x += deltaX;
                y += deltaY;
            }
            x = tmpX;
            y = tmpY;
        }

        empty--;
        if (count[0] + count[4] >= k + 1 || count[1] + count[5] >= k + 1
                || count[2] + count[6] >= k + 1 || count[3] + count[7] >= k + 1) {
            return Result.WIN;
        }
        if (count[0] + count[4] >= 5 || count[1] + count[5] >= 5
                || count[2] + count[6] >= 5 || count[3] + count[7] >= 5) {
            return Result.AnotherOne;
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public void clean() {
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < n; i++) {
            sb.append(' ').append(i);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(' ').append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
