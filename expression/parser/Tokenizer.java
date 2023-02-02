package expression.parser;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
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
        ArrayList<Token> tokens = new ArrayList<>();
        int pos = 0;
        while (pos < expText.length()) {
            char c = expText.charAt(pos);
            if (c == 's') {
                tokens.add(new Token(TokenType.SET, "set"));
                pos++;
                continue;
            }
            if (c == 'c') {
                tokens.add(new Token(TokenType.CLEAR, "clear"));
                pos++;
                continue;
            }
            if (c == 'x' || c == 'y' || c == 'z') {
                tokens.add(new Token(TokenType.VAR, c));
                pos++;
                continue;
            }
            if (c == '(') {
                tokens.add(new Token(TokenType.OPEN_BRACKET, c));
                pos++;
                continue;
            }
            if (c == ')') {
                tokens.add(new Token(TokenType.CLOSE_BRACKET, c));
                pos++;
                continue;
            }
            if (c == '+') {
                tokens.add(new Token(TokenType.ADD, c));
                pos++;
                continue;
            }
            if (c == '-') {
                tokens.add(new Token(TokenType.SUB, c));
                pos++;
                continue;
            }
            if (c == '*') {
                tokens.add(new Token(TokenType.MUL, c));
                pos++;
                continue;
            }
            if (c == '/') {
                tokens.add(new Token(TokenType.DIV, c));
                pos++;
                continue;
            }
            if (c <= '9' && c >= '0') {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(c);
                    pos++;
                    if (pos >= expText.length()) {
                        break;
                    }
                    c = expText.charAt(pos);
                } while (c <= '9' && c >= '0');
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                continue;
            }
            pos++;
        }
        tokens.add(new

                Token(TokenType.EOF, ""));
        return tokens;
    }

}
