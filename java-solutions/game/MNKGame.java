package game;

public class MNKGame {

    public static int initGame(Player player1, Player player2, int m, int n, int k) {
        try {
            GameBoard gameBoard = new GameBoard(m, n, k);
            final Game game = new Game(false, player1, player2);
            int result = game.play(gameBoard);
            System.out.println(System.lineSeparator());
            System.out.println("  Final Board");
            System.out.println(gameBoard);
            switch (result) {
                case 0 ->
                    System.out.println("Draw!");
                case 1 ->
                    System.out.println("Game winner number: 1 (X)!");
                case 2 ->
                    System.out.println("Game winner number: 2 (O)!");
                default ->
                    throw new IllegalArgumentException();
            }
            return result;
        } catch (IllegalArgumentException e) {
            System.out.println("Unexpected situation. Check your game.");
            return -1;
        } catch (RuntimeException e) {
            System.err.println("Something went wrong: " + e.getMessage() + "please restart game.");
            return -1;
        }
    }
}
