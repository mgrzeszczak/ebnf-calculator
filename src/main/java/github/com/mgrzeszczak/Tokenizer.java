package github.com.mgrzeszczak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static github.com.mgrzeszczak.TokenType.EOF;

final class Tokenizer {

    private final static Token EOF_TOKEN = new Token(EOF);
    private List<Token> tokens;
    private final String input;
    private int position;

    Tokenizer(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
        this.position = 0;
        this.tokenize();
    }

    Token next() {
        return position < tokens.size() ? tokens.get(position++) : EOF_TOKEN;
    }

    private void tokenize() {
        String text = input.replaceAll("\\s", "");
        while (!text.isEmpty()) {
            final String current = text;
            TokenMatch match = Arrays.stream(TokenType.values())
                    .filter(t -> t != EOF)
                    .flatMap(t -> mapTokenMatches(t, t.match(current)))
                    .filter(TokenMatch::isFromStart)
                    .sorted(Comparator.comparingInt(TokenMatch::length).reversed())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("cannot find token for " + current));
            String content = text.substring(0, match.length());
            text = text.substring(match.length());
            tokens.add(new Token(match.type(), content));
        }
    }

    private Stream<TokenMatch> mapTokenMatches(TokenType type, Matcher matcher) {
        List<TokenMatch> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(new TokenMatch(type, matcher.start(), matcher.end()));
        }
        return matches.stream();
    }

    private class TokenMatch {

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
}
