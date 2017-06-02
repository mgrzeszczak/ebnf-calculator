package github.com.mgrzeszczak;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum TokenType {

    NUMBER("((\\d+)(\\.\\d+)?)|(\\.\\d+)"),
    PLUS("\\+"),
    MINUS("-"),
    ASTERISK("\\*"),
    SLASH("/"),
    HASH("#"),
    CARET("\\^"),
    OPEN_PARAM("\\("),
    CLOSE_PARAM("\\)"),
    EOF("");

    private final Pattern pattern;


    TokenType(String regexp) {
        this.pattern = Pattern.compile(regexp);
    }

    Matcher match(String input) {
        return pattern.matcher(input);
    }

    boolean matches(String input) {
        return pattern.matcher(input).matches();
    }
}
