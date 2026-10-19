package md2html;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Stack;

public class Md2HtmlParser {
    static private final LinkedHashMap<String, String> TAGS = new LinkedHashMap<>();
    static {
        TAGS.put("**", "strong");
        TAGS.put("*", "em");
        TAGS.put("__", "strong");
        TAGS.put("_", "em");
        TAGS.put("--", "s");
        TAGS.put("`", "code");
        TAGS.put("%", "var");
    }
    static private final LinkedHashMap<String, String> HTML_TAGS = new LinkedHashMap<>();
    static {
        HTML_TAGS.put("&", "&amp;");
        HTML_TAGS.put("<", "&lt;");
        HTML_TAGS.put(">", "&gt;");
    }

    protected Md2HtmlParser(){
    }

    private String parseHeader(String header) {
        int lvl = 0;
        while (lvl < header.length() && header.charAt(lvl) == '#') {
            lvl++;
        }
        if (0 < lvl && header.charAt(lvl) == ' ') {
            String headerText = header.substring(lvl + 1);
            headerText = replaceHTMLSign(headerText);
            return "<h" + lvl + ">" + headerText + "</h" + lvl + ">";
        }

        return null;
    }

    private String replaceHTMLSign(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            String str = String.valueOf(line.charAt(i));
            if ((str.equals("&") || str.equals("<") || str.equals(">"))) {
                sb.append(HTML_TAGS.get(str));
            } else {
                sb.append(str);
            }
        }
        return sb.toString();

    }

    private String parseMarkDown(String text) {
        StringBuilder inLine = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '\\' && i + 1 < text.length()) {
                inLine.append(text.charAt(i + 1));
                i++;
                continue;
            }

            boolean tagFlag = false;

            for (String MDTag : TAGS.keySet()) {
                if (text.startsWith(MDTag, i)) {
                    String htmlTag = TAGS.get(MDTag);
                    tagFlag = true;

                    if ((MDTag.equals("*") || MDTag.equals("_"))) {
                        boolean leftSpace = ((Character.isWhitespace(text.charAt(i - 1))));
                        boolean rightSpace = ((Character.isWhitespace(text.charAt(i + 1))));

                        if (leftSpace && rightSpace) {
                            tagFlag = false;
                            break;
                        }
                    }

                    if (!stack.isEmpty() && stack.peek().equals(MDTag)) {
                        inLine.append("</").append(htmlTag).append(">");
                        stack.pop();
                    } else {
                        inLine.append("<").append(htmlTag).append(">");
                        stack.push(MDTag);
                    }

                    i += MDTag.length() - 1;
                    break;
                }
            }
            if (!tagFlag) {
                inLine.append(c);
            }
        }

        while (!stack.isEmpty()) {
            String mdTag = stack.pop();
            String returnTag = TAGS.get(mdTag);
            int is_close = inLine.lastIndexOf("<" + returnTag + ">");
            if (is_close != -1) {
                inLine.replace(is_close, is_close + ("<" + returnTag + ">").length(), mdTag);
            }
        }

        return inLine.toString();
    }

    protected String md2htmlParse(String block) {
        String header = parseHeader(block);
        if (header != null) {
            return parseMarkDown(header);
        }

        String parText = replaceHTMLSign(block);
        parText = parseMarkDown(parText);
        return "<p>" + parText + "</p>";
    }
}
