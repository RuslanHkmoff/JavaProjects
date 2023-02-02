package expression;

public abstract class UnaryOperation implements AbstractExpression{

    protected AbstractExpression expr;
    public UnaryOperation(AbstractExpression expr) {
        this.expr = expr;
    }



    @Override
    public int evaluate(int x, int y, int z) {
        return makeOperation(expr.evaluate(x, y , z));
    }

    protected abstract int makeOperation(int x);
}
