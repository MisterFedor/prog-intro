package markup;

public class Text implements intoline {
    protected final String text;

    protected Text(final String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(text);
    }

    @Override
    public void toHtml(StringBuilder str){
        str.append(text);
    }
}
