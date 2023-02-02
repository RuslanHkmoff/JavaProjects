package expression;

public class Variable implements AbstractExpression {
    private final String var;

    public Variable(String var) {

        this.var = var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (obj == this) return true;
        Variable other = (Variable) obj;
        return other.var.equals(this.var);
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public double evaluate(double x) {
        return (double) x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (var.equals("x")) {
            return x;
        }
        if (var.equals("y")) {
            return y;
        }
        return z;
    }
}
