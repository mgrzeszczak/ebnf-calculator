package github.com.mgrzeszczak;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void calculate() throws Exception {
        Stream.of(
                new TestCase("2+2", 4),
                new TestCase("2*2", 4),
                new TestCase("2-2", 0),
                new TestCase("2/2", 1),
                new TestCase("2^2", 4),
                new TestCase("#4", 2),
                new TestCase("###256", 2),
                new TestCase("((2))+2*((3-(5*9/2)))", -37)
        ).forEach(TestCase::test);
    }

    private class TestCase {
        private final String input;
        private final double expected;

        private TestCase(String input, double expected) {
            this.input = input;
            this.expected = expected;
        }

        private void test() {
            Tokenizer tokenizer = new Tokenizer(input);
            Parser parser = new Parser(tokenizer);
            double actual = parser.calculate();
            assertTrue(input + " -> expected " + expected + ", got " + actual, expected == actual);
        }
    }

}