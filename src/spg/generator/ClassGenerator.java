package spg.generator;

import spg.generator.structure.Grammar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ClassGenerator {
    public static void generateNonTerminals(Grammar grammar) throws IOException {
        for (int i = 1; i < grammar.nonTerminals.size(); i++) {
            String name = grammar.nonTerminals.get(i).name;
            PrintWriter writer = new PrintWriter(new FileWriter(new File(grammar.prefix + "/" + name + ".java")));
            writer.write("/*Auto-generated code.*/\npackage " + grammar.pkg + ";\n");
            for (String s : grammar.imports) {
                writer.write("import " + s + "\n");
            }
            writer.write("import spg.runtime.Unit;\n");
            writer.write("public class " + name + " extends Unit {\npublic " + name + "(){\nsuper(" + i + ");\n}\n");
            writer.write(grammar.nonTerminals.get(i).fields);
            writer.write("}\n");
            writer.close();
        }
    }
}
