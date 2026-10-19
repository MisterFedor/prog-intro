package game;

import java.util.Random;

public class RandomPlayer implements Player {

    Random random = new Random();

    @Override
    public Move play(Position position, Cell cell) {
        int m = position.getInputM();
        int n = position.getInputN();
        Move move;
        do {
            int row = random.nextInt(m);
            int column = random.nextInt(n);
            move = new Move(row, column, cell);
        } while (!position.isValid(move));
        return move;
    }
}
