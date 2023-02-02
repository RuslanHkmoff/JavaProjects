package expression.exceptions;

import expression.*;

public class CheckedAdd extends AbstractBinaryOperationChecked {
    public CheckedAdd(AbstractExpression expr1, AbstractExpression expr2) {
        super(expr1, expr2);
    }


    @Override
    protected String getOperation() {
        return "+";
    }


    @Override
    protected int makeIntOperation(int x, int y) {
        if ((x > 0 && y>0 && Integer.MAX_VALUE - x < y)
                || x < 0 && y<0 && Integer.MIN_VALUE - x > y){
            throw new OverflowExceptions("overflow");
        }
        return x + y;
    }


}
