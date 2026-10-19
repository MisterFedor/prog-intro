package game;

import java.util.HashMap;
import java.util.Map;

public class Championship {

    private final Player[] playerTypes;
    private final int m;
    private final int n;
    private final int k;

    public Championship(Player[] types, int m, int n, int k) {
        this.playerTypes = types;
        this.m = m;
        this.n = n;
        this.k = k;
    }

    public void tour() {
        Map<Integer, Integer> pts = new HashMap<>();

        for (int i = 1; i <= playerTypes.length; i++) {
            pts.put(i, 0);
        }
        for (int i = 0; i < playerTypes.length; i++) {
            for (int j = 0; j < playerTypes.length; j++) {
                if (i == j) {
                    continue;
                }
                GameBoard gameBoard = new GameBoard(m, n, k);
                final Game game = new Game(false, playerTypes[i], playerTypes[j]);
                int result = game.play(gameBoard);
                switch (result) {
                    case 1 ->
                        pts.put(i + 1, pts.getOrDefault(i + 1, 0) + 3);
                    case 2 ->
                        pts.put(j + 1, pts.getOrDefault(j + 1, 0) + 3);
                    case 0 -> {
                        pts.put(i + 1, pts.getOrDefault(i + 1, 0) + 1);
                        pts.put(j + 1, pts.getOrDefault(j + 1, 0) + 1);
                    }
                }
            }
        }

        for (Map.Entry<Integer, Integer> pos : pts.entrySet()) {
            System.out.println(pos.getKey() + " player |" + ": " + pos.getValue());
        }

    }
}
