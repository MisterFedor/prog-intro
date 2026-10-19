import java.io.IOException;
import java.util.NoSuchElementException;

public class Reverse {
    public static void main(String[] args) {
        int cNew = 10;
        int cWidth = 2;
        try (MyScanner sc = new MyScanner(System.in)) {
            int[][] matrix = new int[cNew][];
            int[] k = new int[cNew];
            int countLines = 0;
            while (sc.hasNextLine()) {
                // System.err.println("*");
                int countTemp = 0;
                int[] temp = new int[cNew];
                while (sc.hasNext()) {
                    if (temp.length == countTemp) {
                        int[] newTemp = new int[cWidth * temp.length];
                        System.arraycopy(temp, 0, newTemp, 0, temp.length);
                        temp = newTemp;
                    }
                    int figures = sc.nextInt();
                    temp[countTemp] = figures;
                    countTemp++;
                }
                if (countLines == matrix.length) {
                    int[][] matrixNew = new int[cWidth * matrix.length][];
                    int[] kNew = new int[cWidth * k.length];
                    System.arraycopy(k, 0, kNew, 0, k.length);
                    System.arraycopy(matrix, 0, matrixNew, 0, matrix.length);
                    matrix = matrixNew;
                    k = kNew;
                }
                matrix[countLines] = temp;
                k[countLines] = countTemp;
                countLines++;

            }

            for (int i = countLines - 1; i >= 0; i--) {
                for (int j = k[i] - 1; j >= 0; j--) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        } catch (NoSuchElementException e) {
            System.err.println("Нет доступных целых чисел: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }
    }
}