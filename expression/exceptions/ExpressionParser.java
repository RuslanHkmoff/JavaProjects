package expression.exceptions;

import expression.*;

import java.util.List;

import static expression.exceptions.ExceptionTokenizer.*;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
        List<Token> tokensList = getTokens(expression);
        TokenBuffer tokens = new TokenBuffer(tokensList);
        return expr(tokens);
    }


    public static AbstractExpression expr(TokenBuffer tokens) {
        Token curr = tokens.next();
        if (curr.type == TokenType.EOF) {
            return null;
        } else {
            tokens.back();
            return parseSetClear(tokens);
        }
    }

    public static AbstractExpression parseSetClear(TokenBuffer tokens) {
        AbstractExpression value = parseAddSub(tokens);
        while (true) {
            Token curr = tokens.next();
            switch (curr.type) {
                case SET -> value = new Set(value, parseAddSub(tokens));
                case CLEAR -> value = new Clear(value, parseAddSub(tokens));
                case EOF, CLOSE_BRACKET -> {
                    tokens.back();
                    return value;
                }
                default -> throw new InvalidTokenException("invalid input");
            }
        }

    }

    public static AbstractExpression parseAddSub(TokenBuffer tokens) {
        AbstractExpression value = parseMulDiv(tokens);
        while (true) {
            Token curr = tokens.next();
            switch (curr.type) {
                case ADD -> value = new CheckedAdd(value, parseMulDiv(tokens));
                case SUB -> value = new CheckedSubtract(value, parseMulDiv(tokens));
                case EOF, CLOSE_BRACKET, CLEAR, SET -> {
                    tokens.back();
                    return value;
                }
                default -> throw new InvalidTokenException("invalid input");
            }
        }
    }

    public static AbstractExpression parseMulDiv(TokenBuffer tokens) {
        AbstractExpression value = parseConstVar(tokens);
        while (true) {
            Token curr = tokens.next();
            switch (curr.type) {
                case MUL -> value = new CheckedMultiply(value, parseConstVar(tokens));
                case DIV -> value = new CheckedDivide(value, parseConstVar(tokens));
                default -> {
                    tokens.back();
                    return value;
                }
            }
        }
    }

    public static AbstractExpression parseConstVar(TokenBuffer tokens) {
        AbstractExpression res;
        Token curr = tokens.next();
        switch (curr.type) {
            case NUMBER:
                res = new Const(Integer.parseInt(curr.value));
                return res;
            case OPEN_BRACKET:
                AbstractExpression value = parseSetClear(tokens);
                curr = tokens.next();
                return value;
            case SUB:
                curr = tokens.next();
                if (curr.type == TokenType.NUMBER) {
                    return (new Const(Integer.parseInt("-" + curr.value)));
                } else {
                    tokens.back();
                    return new CheckedNegate(parseConstVar(tokens));
                }
            case VAR:
                if (curr.value.equals("x") || curr.value.equals("y") || curr.value.equals("z")) {
                    res = new Variable(curr.value + "");
                    return res;
                }
        }
         throw new InvalidTokenException("invalid input, expected Expression actual: '" + curr.value + "'");
    }

    public static void main(String[] args) {
        String s = "10 20" ;
        ExpressionParser p = new ExpressionParser();
        System.out.println(p.parse(s).evaluate(1, 2 , 0));
    }
}