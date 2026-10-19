package markup;

import java.util.*;

public class Strikeout extends AbstractMark implements intoline {

    public Strikeout(final List<Mark> marks) {
        super(marks,"~","s");
    }
}
