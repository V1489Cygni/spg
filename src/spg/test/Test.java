package spg.test;

import spg.runtime.ParseException;
import spg.runtime.Parser;

public class Test {
    public static void main(String[] args) throws ParseException {
        String s = "3 + 5;\n2 * 5 + 2 * (5 + (-3));\n12";
        Parser p = new Parser(new Config());
        L l = (L) p.parse(s);
        l.list.forEach(System.out::println);
    }
}