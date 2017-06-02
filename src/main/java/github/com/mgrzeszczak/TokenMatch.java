package github.com.mgrzeszczak;

final class TokenMatch {

    private final TokenType type;
    private final int from, to;

    TokenMatch(TokenType type, int from, int to) {
        this.type = type;
        this.from = from;
        this.to = to;
    }

    int length() {
        return to - from;
    }

    boolean isFromStart() {
        return from == 0;
    }

    TokenType type() {
        return type;
    }

}
