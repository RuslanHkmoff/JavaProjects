package expression;

public class UnaryMinus extends UnaryOperation {
    public UnaryMinus(AbstractExpression expr) {
        super(expr);
    }

    @Override
    protected int makeOperation(int x) {
        return -x;
    }
    @Override
    public String toString(){
        if (expr.toString().charAt(0)=='-'){

            return "-(" + expr.toString() + ")";
        }return "-(" + expr.toString() + ")";
        //return "-"+expr.toString() ;
    }


    @Override
    public double evaluate(double x) {
        return 0;
    }

    @Override
    public int evaluate(int x) {
        return 0;
    }
}
