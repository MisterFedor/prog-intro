package markup;

import java.util.*;

public abstract class AbstractMark implements Mark {
    protected final List<Mark> marks;
    protected final String sign;
    protected final String HtmlSign;

    protected AbstractMark(final List<Mark> marks, final String sign, final String HtmlSign) {
        this.marks = new ArrayList<>(marks);
        this.sign = sign;
        this.HtmlSign = HtmlSign;
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(sign);
        for (Mark m : marks) {
            m.toMarkdown(str);
        }
        str.append(sign);
    }

    @Override
    public void toHtml(StringBuilder str) {
        str.append("<").append(HtmlSign).append(">");
        for (Mark m : marks) {
            m.toHtml(str);
        }
        str.append("</").append(HtmlSign).append(">");
    }
}