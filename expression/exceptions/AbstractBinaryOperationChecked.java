package expression.exceptions;

import expression.AbstractExpression;


public abstract class AbstractBinaryOperationChecked implements AbstractExpression {
    protected AbstractExpression expr1;
    protected AbstractExpression expr2;

    public AbstractBinaryOperationChecked(AbstractExpression expr1, AbstractExpression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public double evaluate(double x) {
        return 0;
    }

    @Override
    public int evaluate(int x) {
        return 0;
    }

    @Override
    public String toString() {

        return "(" + expr1.toString() + " " + getOperation() + " " + expr2.toString() + ")";
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int ans = 0;
        try {
            ans = makeIntOperation(expr1.evaluate(x, y, z), expr2.evaluate(x, y, z));
        } catch (DivisionByZero e) {
            throw new DivisionByZero("Division by zero");
        } catch (OverflowExceptions e) {
            throw new OverflowExceptions("overflow");
        }
        return ans;
    }


    protected abstract int makeIntOperation(int x, int y);

    protected abstract String getOperation();
}
