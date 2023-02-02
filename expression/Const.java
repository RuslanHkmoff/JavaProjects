package expression;

public class Const implements AbstractExpression {
    // private final long constant;
    private int intConst;
    private double doubleConst;
    private boolean isInt;

    public Const(double doubleConst) {
        this.doubleConst = doubleConst;
    }

    public Const(int intConst) {
        this.intConst = intConst;
        isInt = true;
    }
//
//    public Const(long constant) {
//        this.constant = constant;
//    }

    @Override
    public int evaluate(int x) {
        return intConst;
    }

    public double evaluate(double x) {
        return doubleConst;
    }

    @Override
    public String toString() {
        if (isInt) return "" + intConst;
        return "" + doubleConst;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj != null && getClass() == obj.getClass()) {
            return intConst == ((Const) obj).intConst;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (isInt) return intConst;
        return (int) doubleConst;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return intConst;
    }
}
