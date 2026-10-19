import java.util.*;
import java.io.*;

public class WsppPos {
    public static void main(String[] args) {
        LinkedHashMap<String, IntList> wordPos = new LinkedHashMap<>();
        int pos = 1;
        String word;
        try (MyScanner sc = new MyScanner(args[0])) {
            int countLine = 0;
            while (sc.hasNextLine()) {
                countLine++;
                while (sc.hasNext()) {
                    word = sc.nextWordSymb().toLowerCase();
                    IntList counts = wordPos.get(word);
                    if (counts == null) {
                        counts = new IntList();
                        wordPos.put(word, counts);
                    }
                    counts.addMod(countLine, pos);
                    pos++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось открыть файл: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода " + e.getMessage());
        }

        int total = pos - 1;
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) { //
            for (Map.Entry<String, IntList> dict : wordPos.entrySet()) {
                IntList temp = dict.getValue();
                writer.write(dict.getKey() + " " + temp.size());
                for (int i = 0; i < temp.size(); i++) {
                    int reverse = total - temp.getPos(i) + 1;
                    writer.write(" " + temp.getLine(i) + ":" + reverse);
                }
                writer.newLine();
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Введён неправильный индекс: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }
    }
}
