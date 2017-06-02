package github.com.mgrzeszczak;

import java.util.Arrays;
import java.util.stream.Collectors;

import static github.com.mgrzeszczak.TokenType.*;

final class Parser {

    private Tokenizer tokenizer;
    private Token token;

    Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.token = tokenizer.next();
    }

    public double calculate() {
        return parseExpression();
    }

    private double parseExpression() {
        double value = parseTerm();
        while (any(PLUS, MINUS)) {
            TokenType op = token.getType();
            move();
            value = op == PLUS ? value + parseTerm() : value - parseTerm();
        }
        return value;
    }

    private double parseTerm() {
        double value = parseFactor();
        while (any(ASTERISK, SLASH)) {
            TokenType op = token.getType();
            move();
            double right = parseFactor();
            if (op == ASTERISK) {
                value = value * right;
            } else if (right != 0){
                value = value / right;
            } else {
                throw new IllegalArgumentException("cannot divide by 0");
            }
        }
        return value;
    }

    private double parseFactor() {
        double value = parseExponent();
        if (match(CARET)) {
            move();
            value = Math.pow(value, parseFactor());
        }
        return value;
    }

    private double parseExponent() {
        if (match(HASH)) {
            move();
            return Math.sqrt(parseExponent());
        } else {
            return parseRoot();
        }
    }

    private double parseRoot() {
        if (match(OPEN_PARAM)) {
            move();
            double exp = parseExpression();
            assertMatch(CLOSE_PARAM);
            move();
            return exp;
        }
        assertMatch(NUMBER);
        double value = token.value();
        move();
        return value;
    }

    private boolean match(TokenType type) {
        return this.token.getType() == type;
    }

    private boolean any(TokenType... types) {
        return Arrays.stream(types).anyMatch(t -> t == token.getType());
    }

    private void assertMatch(TokenType type) {
        if (!match(type))
            throw new IllegalStateException("expected " + type.name() + " got " + this.token.getType());
    }

    private void assertAny(TokenType... types) {
        if (!any(types)) {
            throw new IllegalStateException("expected any of " + Arrays.stream(types).map(TokenType::name).collect(Collectors.joining(", ")) + " got " + this.token.getType());
        }
    }

    private void move() {
        this.token = tokenizer.next();
    }
}
