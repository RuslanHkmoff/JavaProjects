package expression.exceptions;

import expression.AbstractExpression;

public class CheckedSubtract extends AbstractBinaryOperationChecked {
    public CheckedSubtract(AbstractExpression expr1, AbstractExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getOperation() {
        return "-";
    }


    @Override
    protected int makeIntOperation(int x, int y) {
        if ((x == 0 && y == Integer.MIN_VALUE)
                || (y > 0 && Integer.MIN_VALUE + y > x)
                || y < 0 && Integer.MAX_VALUE + y < x) {
            throw new OverflowExceptions("overflow");
        }
        return x - y;
    }
}
