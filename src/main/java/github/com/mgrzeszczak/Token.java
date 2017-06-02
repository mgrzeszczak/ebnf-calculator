package github.com.mgrzeszczak;

final class Token {

    private final TokenType type;
    private final String content;

    Token(TokenType type) {
        this.type = type;
        this.content = "";
    }

    Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    TokenType getType() {
        return type;
    }

    String getContent() {
        return content;
    }

    double value() {
        if (type != TokenType.NUMBER) {
            throw new IllegalArgumentException("cannot get value of " + type.name());
        }
        return Double.parseDouble(content);
    }
}
