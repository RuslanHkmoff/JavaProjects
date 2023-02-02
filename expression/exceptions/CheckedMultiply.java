package expression.exceptions;

import expression.AbstractExpression;

public class CheckedMultiply extends AbstractBinaryOperationChecked {
    public CheckedMultiply(AbstractExpression expr1, AbstractExpression expr2) {
        super(expr1, expr2);
    }


    @Override
    protected String getOperation() {
        return "*";
    }


    @Override
    protected int makeIntOperation(int x, int y) {
        if (x > 0 && y > 0 && x > Integer.MAX_VALUE/y){
            throw new OverflowExceptions("overflow");
        } else if (x > 0 && y < 0 && y < Integer.MIN_VALUE/x){
            throw new OverflowExceptions("overflow");
        } else if (x < 0 && y > 0 && x < Integer.MIN_VALUE/y){
            throw new OverflowExceptions("overflow");
        } else if (x < 0 && y < 0 && y < Integer.MAX_VALUE/x){
            throw new OverflowExceptions("overflow");
        }
        return x * y;
    }
}
