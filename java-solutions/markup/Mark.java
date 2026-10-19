package markup;

public interface Mark {
    public void toMarkdown(StringBuilder str);
    public void toHtml(StringBuilder str);
}