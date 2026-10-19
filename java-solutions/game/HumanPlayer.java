package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
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
    public Move play(final Position position, final Cell cell) {
        while (true) {
            try {
                out.println(System.lineSeparator());
                out.println("  Board");
                out.println(position);
                out.println(cell + "'s move");
                out.println("Enter row and column: ");
                final Move move = new Move(in.nextInt() - 1, in.nextInt() - 1, cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println("Move is invalid. Check your input and try again.");

            } catch (InputMismatchException e) {
                out.println("Wrong input. Write just 2 numbers.");
                in.nextLine();
            }
        }
    }
}
