package markup;

import java.util.*;

public class Emphasis extends AbstractMark implements intoline {

    public Emphasis(final List<Mark> marks) {
        super(marks,"*","em");
    }
}
