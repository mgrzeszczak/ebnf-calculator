package github.com.mgrzeszczak;

import org.junit.Test;

import java.util.stream.Stream;

import static github.com.mgrzeszczak.TokenType.*;
import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void testValidInputs() {
        Stream.of(
                new TestCase(
                        "22+2",
                        Stream.of(NUMBER, PLUS, NUMBER, EOF, EOF)
                ),
                new TestCase(
                        "1.222*2",
                        Stream.of(NUMBER, ASTERISK, NUMBER, EOF, EOF)
                ),
                new TestCase(
                        "22/2",
                        Stream.of(NUMBER, SLASH, NUMBER, EOF, EOF)
                ),
                new TestCase(
                        "22*#2",
                        Stream.of(NUMBER, ASTERISK, HASH, NUMBER, EOF, EOF)
                ),
                new TestCase(
                        "* / + - ^ # 22.1223 .1230 ( ) ) (",
                        Stream.of(ASTERISK, SLASH, PLUS, MINUS, CARET, HASH, NUMBER, NUMBER,
                                OPEN_PARAM, CLOSE_PARAM, CLOSE_PARAM, OPEN_PARAM)
                )
        ).forEach(TestCase::test);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testErrors() {
        String invalidInput = "22 + 2 + aa";
        new Tokenizer(invalidInput);
    }

    private class TestCase {
        private final String input;
        private final Stream<TokenType> tokenTypes;

        private TestCase(String input, Stream<TokenType> tokenTypes) {
            this.input = input;
            this.tokenTypes = tokenTypes;
        }

        private void test() {
            System.out.println("testing " + input);
            Tokenizer tokenizer = new Tokenizer(input);
            tokenTypes
                    .forEach(s -> assertTrue(tokenizer.next().getType() == s));
        }
    }

}