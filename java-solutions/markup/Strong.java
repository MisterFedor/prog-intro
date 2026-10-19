package markup;

import java.util.*;

public class Strong extends AbstractMark implements intoline {

    public Strong(final List<Mark> marks) {
        super(marks,"__","strong");
    }
}
