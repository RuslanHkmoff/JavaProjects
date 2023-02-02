package expression.exceptions;

import expression.AbstractExpression;

public class CheckedDivide extends AbstractBinaryOperationChecked {


    public CheckedDivide(AbstractExpression expr1, AbstractExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getOperation() {
        return "/";
    }


    @Override
    protected int makeIntOperation(int x, int y) {
        if (y == 0) throw new DivisionByZero("division by zero");
        if (x == Integer.MIN_VALUE && y == -1) throw new OverflowExceptions("overflow");
        return x / y;
    }
}
