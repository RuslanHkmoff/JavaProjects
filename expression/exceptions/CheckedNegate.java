package expression.exceptions;

import expression.AbstractExpression;

public class CheckedNegate extends UnaryOperationChecked {
    public CheckedNegate(AbstractExpression expr) {
        super(expr);
    }

    @Override
    public String toString() {
        return "-(" + expr.toString() + ")";
    }

    @Override
    protected int makeOperation(int x) {
        if (x == Integer.MIN_VALUE) throw new OverflowExceptions("overflow");
        return -x;
    }

    @Override
    public double evaluate(double x) { return 0; }

    @Override
    public int evaluate(int x) { return 0; }
}
