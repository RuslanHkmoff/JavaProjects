package expression.exceptions;

import expression.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class ExceptionTokenizer {
    public enum TokenType {
        OPEN_BRACKET, CLOSE_BRACKET,
        ADD, SUB, MUL, DIV,
        VAR,
        NUMBER, SET,
        CLEAR,
        EOF
    }

    public static class Token {
        TokenType type;
        String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }


        public Token(TokenType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }
    }

    public static class TokenBuffer {
        private int pos;

        public List<Token> tokens;

        public TokenBuffer(List<Token> tokens) {
            this.tokens = tokens;
        }

        public Token next() {
            return tokens.get(pos++);
        }

        public void back() {
            pos--;
        }
    }


    public static List<Token> getTokens(String expText) {
        String prev = "";
        ArrayList<Token> tokens = new ArrayList<>();
        int pos = 0;
        String maxValue = "" + Integer.MAX_VALUE;
        int bracketsBalance = 0;
        while (pos < expText.length()) {
            char c = expText.charAt(pos);
            if (bracketsBalance < 0) {
                throw new RuntimeException("Illegal counts of brackets");
            } else if (Character.isWhitespace(c)) {
                pos++;
                continue;
            }
            if (c == 's') {
                tokens.add(new Token(TokenType.SET, "set"));
                pos += 3;
                continue;
            } else if (c == 'c') {
                tokens.add(new Token(TokenType.CLEAR, "clear"));
                pos += 5;
                continue;
            } else if (c == 'x' || c == 'y' || c == 'z') {
                tokens.add(new Token(TokenType.VAR, c));
                pos++;
                if (pos < expText.length()) {
                    if (expText.charAt(pos) == 's' || expText.charAt(pos) == 'c') {
                        throw new InvalidTokenException("unexpected symbol '" + expText.charAt(pos) + "'");
                    }
                }
                continue;
            } else if (c == '(') {
                bracketsBalance++;
                tokens.add(new Token(TokenType.OPEN_BRACKET, c));
                pos++;
                prev = "(";
                continue;
            } else if (c == ')') {
                bracketsBalance--;
                tokens.add(new Token(TokenType.CLOSE_BRACKET, c));
                pos++;
                prev = ")";
                continue;
            } else if (c == '+') {
                tokens.add(new Token(TokenType.ADD, c));
                pos++;
                prev = "+";
                continue;
            } else if (c == '-') {
                tokens.add(new Token(TokenType.SUB, c));
                pos++;
                prev = "-";
                continue;
            } else if (c == '*') {
                tokens.add(new Token(TokenType.MUL, c));
                pos++;
                prev = "*";
                continue;
            } else if (c == '/') {
                tokens.add(new Token(TokenType.DIV, c));
                pos++;
                prev = "/";
                continue;
            } else if (c <= '9' && c >= '0') {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(c);
                    pos++;
                    if (pos >= expText.length()) {
                        break;
                    }
                    c = expText.charAt(pos);
                } while (c <= '9' && c >= '0');
                if (pos < expText.length()) {
                    if (expText.charAt(pos) == 's' || expText.charAt(pos) == 'c') {
                        throw new InvalidTokenException("unexpected symbol '" + expText.charAt(pos) + "'");
                    }
                }
                if (prev.equals("-")
                        && sb.toString().equals("2147483648")) {
                    tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                    continue;
                }
                try {
                    Integer.parseInt(sb.toString());
                } catch (NumberFormatException e) {
                    throw new IllegalConstException("Illegal const '" + sb + "'");
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                continue;
            } else {
                throw new InvalidTokenException("unexpexted token '" + c + "'");
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        if (bracketsBalance != 0) {
            throw new BracketsException("Illegal counts of brackets");
        }
        return tokens;
    }

}
