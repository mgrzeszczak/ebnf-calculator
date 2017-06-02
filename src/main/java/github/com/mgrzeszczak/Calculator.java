package github.com.mgrzeszczak;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(">> ");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                Tokenizer tokenizer = new Tokenizer(input);
                Parser parser = new Parser(tokenizer);
                System.out.println(parser.calculate());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.print(">> ");
        }
    }

}
