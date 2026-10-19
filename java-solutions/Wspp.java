import java.util.*;
import java.io.*;

public class Wspp {
    public static void main(String[] args) {
        LinkedHashMap<String, IntList> wordPos = new LinkedHashMap<>();
        int pos = 1;
        try (MyScanner sc = new MyScanner(args[0])) {
            String word = sc.nextWord();
            while (word != null) {
                word = word.toLowerCase();
                if (!wordPos.containsKey(word)) {
                    wordPos.put(word, new IntList());
                }
                wordPos.get(word).add(pos);
                pos++;
                word = sc.nextWord();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось открыть файл: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) {
            for (Map.Entry<String, IntList> dict : wordPos.entrySet()) {
                IntList answer = dict.getValue();
                writer.write(dict.getKey() + " " + answer.size());
                for (int i = 0; i < answer.size(); i++) {
                    writer.write(" " + answer.get(i));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }
    }
}
