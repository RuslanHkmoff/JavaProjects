package expression;

import javax.naming.BinaryRefAddr;
import java.util.BitSet;

public class Clear extends AbstractBinaryOperation{
    public Clear(AbstractExpression expr1, AbstractExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getOperation() {
        return "clear";
    }

    @Override
    protected double makeDoubleOperation(double x, double y, String op) {
        return 0;
    }

    @Override
    protected int makeIntOperation(int x, int y, String op) {
        return x & ~(1 <<y);
    }
}
