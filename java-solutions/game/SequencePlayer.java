package game;

public class SequencePlayer implements Player {

    @Override
    public Move play(Position position, Cell cell) {
        int rows = position.getInputM();
        int columns = position.getInputN();
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        return new Move(0, 0, cell);
    }
}
