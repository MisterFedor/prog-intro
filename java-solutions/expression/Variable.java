package expression;

import java.util.Objects;

public class Variable implements GeneralExpr {

    private final String var;

    public Variable(String var) {
        this.var = var;
    }

//    public Variable(int x){
//        this.var="$"+x;
//    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.var, ((Variable) obj).var);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(var);
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (var) {
            case "x" ->
                x;
            case "y" ->
                y;
            case "z" ->
                z;
            default ->
                throw new IllegalArgumentException("Unexpected variable " + var);
        };
    }
}
