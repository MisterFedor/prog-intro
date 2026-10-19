package markup;

import java.util.*;


public class Paragraph extends AbstractMark implements outline {
    public Paragraph(final List<intoline> marks){
        super(new ArrayList<>(marks),"","p");
    }
}
    // protected final List<Mark> marks;

    // public Paragraph(final List<Mark> marks) {
    //     this.marks = new ArrayList<>(marks);
    // }
    // @Override
    // public void toMarkdown(StringBuilder str) {
    //     for (Mark m : marks) {
    //         m.toMarkdown(str);
    //     }
    // }

    // @Override
    // public void toHtml(StringBuilder str) {
    //     str.append("<p>");
    //     for (Mark m : marks) {
    //         m.toHtml(str);
    //     }
    //     str.append("</p>");
    // }