package expression;

import java.util.BitSet;

public class Set extends AbstractBinaryOperation {
    public Set(AbstractExpression expr1, AbstractExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getOperation() {
        return "set";
    }

    @Override
    protected double makeDoubleOperation(double x, double y, String op) {
        return 0;
    }

    @Override
    protected int makeIntOperation(int x, int y, String op) {
        return x | (1 << y);
    }
}
