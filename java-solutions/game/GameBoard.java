package game;

import java.util.Arrays;
import java.util.Map;

public class GameBoard implements Board, Position {

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final int m;
    private final int n;
    private final int k;
    private final Cell[][] cells;
    private Cell turn;

    public GameBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
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
    public int getInputM() {
        return m;
    }

    @Override
    public int getInputN() {
        return n;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        for (int row = 0; row < m; row++) {
            int rowCount = 0;
            for (int col = 0; col < n; col++) {
                if (cells[row][col] == turn) {
                    rowCount++;
                    if (rowCount == k) {
                        return Result.WIN;
                    }
                } else {
                    rowCount = 0;
                }
            }
        }

        for (int col = 0; col < n; col++) {
            int colCount = 0;
            for (int row = 0; row < m; row++) {
                if (cells[row][col] == turn) {
                    colCount++;
                    if (colCount == k) {
                        return Result.WIN;
                    }
                } else {
                    colCount = 0;
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int cellsCnt = 0;
                for (int i = 0; i < k; i++) {
                    if (row + i < m && col + i < n && cells[row + i][col + i] == turn) {
                        cellsCnt++;
                        if (cellsCnt == k) {
                            return Result.WIN;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int cellsCnt = 0;
                for (int i = 0; i < k; i++) {
                    if (row + i < m && col - i < n && col - i >= 0
                            && cells[row + i][col - i] == turn) {
                        cellsCnt++;
                        if (cellsCnt == k) {
                            return Result.WIN;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        int markedCnt = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (cells[row][col] != Cell.E) {
                    markedCnt++;
                }
            }
        }
        if (markedCnt == m * n) {
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
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int c = 1; c <= n; c++) {
            sb.append(" ").append(c).append(" ");
        }
        sb.append(System.lineSeparator());

        for (int r = 0; r < m; r++) {
            sb.append(r + 1);
            for (int c = 0; c < n; c++) {
                sb.append(" ").append(SYMBOLS.get(cells[r][c])).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
