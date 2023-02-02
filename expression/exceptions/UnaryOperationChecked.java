package expression.exceptions;

import expression.AbstractExpression;

public abstract class UnaryOperationChecked implements AbstractExpression {
    protected AbstractExpression expr;
    public UnaryOperationChecked(AbstractExpression expr) {
        this.expr = expr;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeOperation(expr.evaluate(x, y , z));
    }
    protected abstract int makeOperation(int x);
}
