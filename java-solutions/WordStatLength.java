import java.util.*;
import java.io.*;

public class WordStatLength {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<>();
        try (MyScanner sc = new MyScanner(args[0])) {
            String word = sc.nextWord();
            while (word != null) {
                word = word.toLowerCase();
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                word = sc.nextWord();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось открыть файл: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(wordCount.entrySet());
        sorted.sort(Comparator.comparingInt(s -> s.getKey().length()));

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) {
            for (Map.Entry<String, Integer> dict : sorted) {
                writer.write(dict.getKey() + " " + dict.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }

    }
}