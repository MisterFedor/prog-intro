package md2html;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BaseParser {
    private final Md2HtmlParser parser;
    private static final Charset CODING = StandardCharsets.UTF_8;

    protected BaseParser(Md2HtmlParser parser) {
        this.parser = parser;
    }

    protected void parseFile(String inputFile, String outputFile) {
        String lineSep = System.lineSeparator();
        int sepLen = lineSep.length();
        List<String> readedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFile), CODING))) {
            char[] buffer = new char[1024];
            int readedChars;
            int sep_index = 0;
            StringBuilder strings = new StringBuilder();
            while ((readedChars = reader.read(buffer)) > 0) {
                for (int i = 0; i < readedChars; i++) {
                    char c = buffer[i];
                    if (c == lineSep.charAt(sep_index)) {
                        sep_index++;
                        if (sep_index == sepLen) {
                            readedLines.add(strings.toString());
                            sep_index = 0;
                            strings.setLength(0);
                        }
                    } else {
                        strings.append(c);
                    }
                }
            }
            if (strings.length() > 0) {
                readedLines.add(strings.toString());
            }
        } catch (IOException e) {
            System.err.println("Input file error: " + e.getMessage());
        }

        List<String> blocks = new ArrayList<>();
        StringBuilder block = new StringBuilder();
        for (int i = 0; i < readedLines.size(); i++) {
            String lines = readedLines.get(i);
            if (lines.isEmpty()) {
                if (block.length() > 0) {
                    blocks.add(block.toString());
                    block.setLength(0);
                }
            } else {
                block.append(lines);
                if (i + 1 < readedLines.size() && !readedLines.get(i + 1).isEmpty()) {
                    block.append(lineSep);
                }
            }
        }
        if (block.length() > 0) {
            blocks.add(block.toString());
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFile), CODING))) {
            for (String blocks_ToParse : blocks) {
                String html = parser.md2htmlParse(blocks_ToParse);
                writer.write(html);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Write file error: " + e.getMessage());
        }
    }
}
