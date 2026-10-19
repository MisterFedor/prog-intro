package game;

public class BadPlayer implements Player {

    @Override
    public Move play(Position position, Cell cell) {
        throw new RuntimeException();
    }
}
