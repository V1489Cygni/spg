package spg.test;

import spg.runtime.Lexer;
import spg.runtime.ParseException;
import spg.runtime.Parser;

public class Test {
    public static void main(String[] args) throws ParseException {
        Parser parser = new Parser(new Lexer(), new ConfigurationImpl());
        NT1 nt1 = (NT1) parser.parse("00011101");
        System.out.println(nt1.n);
    }
}
