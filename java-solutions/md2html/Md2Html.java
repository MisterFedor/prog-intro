package md2html;

public class Md2Html {
    public static void main(String[] args) {
        Md2HtmlParser html_parser = new Md2HtmlParser();
        BaseParser base_parser = new BaseParser(html_parser);

        base_parser.parseFile(args[0], args[1]);
    }
}
