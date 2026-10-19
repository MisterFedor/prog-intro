import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class MyScanner implements AutoCloseable {
    private final Reader reader;
    private final int buffer_size = 1024;
    private final char[] buffer = new char[buffer_size];
    private final String line_sep = System.lineSeparator();
    private int sep = 0;
    private int count = 0;
    private int pos = 0;
    private boolean end = false;
    private String readPart = null;

    public MyScanner(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
    }

    public MyScanner(String file) throws FileNotFoundException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
    }

    private boolean check() throws IOException {
        if (end) {
            return false;
        }
        if (pos < count) {
            return true;
        }
        count = reader.read(buffer);
        pos = 0;
        if (count == -1) {
            end = true;
            return false;
        }
        return true;
    }

    private boolean checkLineSep(char c) throws IOException {
        check();
        if (c == line_sep.charAt(sep)) {
            sep++;
            if (sep == line_sep.length()) {
                sep = 0;
                return true;
            }
            return false;
        } else {
            sep = 0;
            return false;
        }
    }

    private boolean isSep(char c) {
        int type = Character.getType(c);
        if (Character.isLetterOrDigit(c) || type == Character.DASH_PUNCTUATION || c == '\'' ||
        c=='$' || c=='_') {
            return false;
        }
        return true;
    }

    private boolean isWord(char c) {
        int type = Character.getType(c);
        return Character.isLetter(c) || type == Character.DASH_PUNCTUATION || c == '\'';
    }

    private boolean isWordSymb(char c) {
        int type = Character.getType(c);
        return Character.isLetterOrDigit(c) || type == Character.DASH_PUNCTUATION || c == '\'' ||
                c == '$' || c == '_';
    }

    public boolean hasNext() throws IOException {
        while (pos < count || check()) {
            if (!isSep(buffer[pos])) {
                return true;
            }
            if (checkLineSep(buffer[pos])) {
                pos++;
                return false;
            }
            pos++;
        }
        return false;
    }

    public String next() throws IOException {
        StringBuilder str = new StringBuilder();
        while ((pos < count || check()) && isSep(buffer[pos])) {
            pos++;
        }
        if (!check()) {
            return null;
            // throw new IOException("End of input");
        }
        while (check() && !isSep(buffer[pos])) {
            str.append(buffer[pos]);
            pos++;
        }
        return str.toString();
    }

    public boolean hasNextLine() throws IOException {
        return pos < count || check();
    }

    public boolean hasNextInt() throws IOException {
        if (readPart != null) {
            try {
                Integer.parseInt(readPart);
                return true;
            } catch (NumberFormatException e) {
                readPart = null;
            }
        }

        if (readPart == null) {
            readPart = next();
            try {
                Integer.parseInt(readPart);
                return true;
            } catch (NumberFormatException e) {
                readPart = next();
            }
        }
        return false;
    }

    public int nextInt() throws NoSuchElementException, IOException {
        if (!hasNextInt()) {
            throw new NoSuchElementException("Int unavailable");
        }
        int figure = Integer.parseInt(readPart);
        readPart = null;
        return figure;
    }

    public String nextWord() throws IOException {
        StringBuilder word = new StringBuilder();
        while ((pos < count || check()) && !isWord(buffer[pos])) {
            pos++;
        }
        if (!check()) {
            return null;
        }
        while (check() && isWord(buffer[pos])) {
            word.append(buffer[pos]);
            pos++;
        }
        return word.toString();
    }

    public String nextWordSymb() throws IOException {
        StringBuilder words = new StringBuilder();
        while ((pos < count || check()) && !isWordSymb(buffer[pos])) {
            pos++;
        }
        if (!check()) {
            return null;
        }
        while (check() && isWordSymb(buffer[pos])) {
            words.append(buffer[pos]);
            pos++;
        }
        return words.toString();
    }

    public void close() throws IOException {
        reader.close();
    }
}

// public boolean hasNextLine() throws IOException {
// if (count == -1) { // конец входа
// return false;
// }
// if (count == 0) { // начало чтения
// check();
// if (count == -1) {
// return false;
// }
// return true;
// }
// if (pos == count) { // закончили чтение ровно по длине буффера
// check();
// if (count == -1) {
// return false;
// }
// return true;
// }
// if (pos < count) { // стандартный случай
// return true;
// }
// return false;
// }

// public String nextLine() throws IOException {
// if (count == -1) { // конец входа
// // ТщЫгсрУдуьуте
// throw new IOException("No new Line");
// } else if (count == 0) { // начало чтения, буффер не инициализирован
// check();
// if (count == -1) {
// throw new IOException("No new Line");
// }
// } else if (pos == count) { // плохой случай чтение закончилось на конце
// check();
// if (count == -1) {
// throw new IOException("No new Line");
// }
// }
// StringBuilder sb = new StringBuilder();
// while (pos < count) {
// char c = buffer[pos];
// if (c != '\n') { // System.lineSeparator()
// sb.append(c);
// } else {
// pos++;
// return sb.toString();
// }
// pos++;
// if (pos == count) {
// check();
// }
// }
// return sb.toString();
// }

// public boolean hasNextLine() throws IOException {
// if (!check()) {
// return false;
// }
// return true;
// }

// public boolean hasNextInt() throws IOException {
// check();
// int tPos = pos;
// while (true) {
// if (!check()) {
// return false;
// }
// if (tPos >= count) {
// check();
// if (count == -1) {
// return false;
// }
// tPos = 0;
// }

// if (!Character.isWhitespace(buffer[tPos])) {
// break;
// }
// tPos++;
// }

// char c = buffer[tPos];
// if (c == '-' || Character.isDigit(c)) {
// return true;
// }
// return false;
// }
