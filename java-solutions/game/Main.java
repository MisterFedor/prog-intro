package game;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int gameChoice;
            while (true) {
                try {
                    System.out.println("MNK Game - 1");
                    System.out.println("Two round tournament - 2");
                    System.out.println("Please, choose your game mode: ");
                    gameChoice = scanner.nextInt();
                    if (gameChoice == 1 || gameChoice == 2) {
                        break;
                    }
                    System.out.println("Write correct game mode number, 1 or 2.");
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Write correct game mode number, 1 or 2.");
                    scanner.nextLine();
                }
            }

            int m;
            int n;
            int k;
            while (true) {
                try {
                    System.out.println("Write number of rows, columns and win condition: ");
                    m = scanner.nextInt();
                    n = scanner.nextInt();
                    k = scanner.nextInt();
                    if (m < 1 || n < 1 || k < 1) {
                        System.out.println("Number of rows, columns and wind condition must be > 0.");
                        scanner.nextLine();
                        continue;
                    }
                    if (m < k && n < k) {
                        System.out.println("Win condition more than number of rows and columns.");
                        scanner.nextLine();
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Something with input rows, columns and win condition went wrong. Write three numbers.");
                    scanner.nextLine();
                }
            }
            int countPlayers;
            if (gameChoice == 1) {
                countPlayers = 2;
            } else {
                while (true) {
                    try {
                        System.out.println("Choose numbers of players: ");
                        countPlayers = scanner.nextInt();
                        scanner.nextLine();
                        if (countPlayers > 1) {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Write correct numbers of players. Only one number.");
                        scanner.nextLine();
                    }
                }
            }
            boolean correctChoice;
            Player[] playersType = new Player[countPlayers];
            for (int i = 0; i < countPlayers; i++) {
                correctChoice = false;
                while (!correctChoice) {
                    try {
                        System.out.println("1 - Human Player");
                        System.out.println("2 - Sequance Player");
                        System.out.println("3 - Random Player");
                        System.out.println("4 - Bad Player");
                        System.out.println("Input your choice for player " + (i + 1) + ":");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (userChoice) {
                            case 1 ->
                                playersType[i] = new HumanPlayer();
                            case 2 ->
                                playersType[i] = new SequencePlayer();
                            case 3 ->
                                playersType[i] = new RandomPlayer();
                            case 4 ->
                                playersType[i] = new BadPlayer();
                            default -> {
                                System.out.println("Write correct player type.");
                                continue;
                            }
                        }
                        correctChoice = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Write correct player type. Only one number.");
                        scanner.nextLine();
                    }
                }

            }
            if (gameChoice == 1) {
                int res = MNKGame.initGame(playersType[0], playersType[1], m, n, k);
                if (res < 0) {
                    System.out.println("Unexcpected situation, try again later.");
                }
            } else {
                Championship champ = new Championship(playersType, m, n, k);
                champ.tour();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Thanks for game.");
        }
    }
}
