package expression;

import java.util.Objects;

public abstract class AbstractBinaryOperation implements AbstractExpression {

    protected AbstractExpression expr1;
    protected AbstractExpression expr2;

    public AbstractBinaryOperation(AbstractExpression expr1, AbstractExpression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public int evaluate(int x) {
        return makeIntOperation(expr1.evaluate(x), expr2.evaluate(x), getOperation());
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeIntOperation(expr1.evaluate(x, y, z), expr2.evaluate(x, y, z), getOperation());
    }

    @Override
    public double evaluate(double x) {
        return makeDoubleOperation(expr1.evaluate(x), expr2.evaluate(x), getOperation());
    }


    @Override
    public String toString() {

        return "(" + expr1.toString() + " " + getOperation() + " " + expr2.toString() + ")";
    }


    @Override
    public boolean equals(Object ob) {
        if (ob == null || getClass() != ob.getClass()) return false;
        if (ob == this) return true;
        final var otherExpr = (AbstractBinaryOperation) ob;
        return expr1.equals(((AbstractBinaryOperation) ob).expr1)
                && expr2.equals(((AbstractBinaryOperation) ob).expr2)
                && this.getOperation().equals(getOperation());
    }



    @Override
    final public int hashCode() {
        return 31*(31 * (31 * expr1.hashCode() + 31) + expr2.hashCode()) + this.getClass().hashCode();
       //return Objects.hash(this.getClass(), expr1, expr2) * 31;
    }


    protected abstract String getOperation();

    protected abstract double makeDoubleOperation(double x, double y, String op);

    protected abstract int makeIntOperation(int x, int y, String op);
}
