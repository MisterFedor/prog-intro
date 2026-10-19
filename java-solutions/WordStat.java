import java.util.*;

import java.io.*;

public class WordStat {
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
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) {
            for (Map.Entry<String, Integer> dict : wordCount.entrySet()) {
                writer.write(dict.getKey() + " " + dict.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}