package spg.generator;

import spg.generator.structure.Grammar;
import spg.generator.structure.NonTerminalDescriptor;
import spg.generator.structure.Rule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ClassGenerator {
    public static void generateClasses(Grammar grammar) throws IOException {
        generateNonTerminals(grammar);
        generateRuleManager(grammar);
    }

    private static void generateNonTerminals(Grammar grammar) throws IOException {
        for (int i = 0; i < grammar.nonTerminals.size(); i++) {
            PrintWriter writer = new PrintWriter(new FileWriter(new File(grammar.prefix + "/NT" + i + ".java")));
            writer.write("package " + grammar.pkg + ";\n");
            for (String s : grammar.imports) {
                writer.write(s + "\n");
            }
            writer.write("import spg.runtime.Unit;\n");
            writer.write("public class NT" + i + " extends Unit {\npublic NT" + i + "(){\nsuper(" + i + ");\n}\n");
            NonTerminalDescriptor descriptor = grammar.nonTerminals.get(i);
            for (int j = 0; j < descriptor.attributes.size(); j++) {
                writer.write("public " + descriptor.types.get(j) + " " + descriptor.attributes.get(j) + ";\n");
            }
            writer.write("}\n");
            writer.close();
        }
    }

    private static void generateRuleManager(Grammar grammar) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(grammar.prefix + "/RuleManagerImpl.java")));
        writer.write("package " + grammar.pkg + ";\n");
        for (String s : grammar.imports) {
            writer.write(s + "\n");
        }
        writer.write("import spg.runtime.*;\nimport java.util.List;\nimport java.util.ArrayList;\n");
        writer.write("public class RuleManagerImpl implements RuleManager{\n");
        for (int i = 0; i < grammar.rules.size(); i++) {
            Rule rule = grammar.rules.get(i);
            String type = "NT" + rule.nonTerminal;
            writer.write("private " + type + " rule" + i + "(");
            for (int j = 0; j < rule.rule.size(); j++) {
                int u = rule.rule.get(j);
                if (u >= grammar.nonTerminals.size()) {
                    writer.write("Terminal $" + j + (j == rule.rule.size() - 1 ? "" : ","));
                } else {
                    writer.write("NT" + u + " $" + j + (j == rule.rule.size() - 1 ? "" : ","));
                }
            }
            writer.write(") {\n" + type + " res=new " + type + "();\n" + rule.action + "return res;\n}\n");
        }
        writer.write("public Unit reduce(int n,List<Unit>r){\nswitch(n){\n");
        for (int i = 0; i < grammar.rules.size(); i++) {
            writer.write("case " + i + ":\nreturn rule" + i + "(");
            Rule rule = grammar.rules.get(i);
            for (int j = 0; j < rule.rule.size(); j++) {
                int u = rule.rule.get(j);
                if (u >= grammar.nonTerminals.size()) {
                    writer.write("(Terminal)");
                } else {
                    writer.write("(NT" + u + ")");
                }
                writer.write("r.get(" + j + ")" + (j == rule.rule.size() -1 ? "" : ","));
            }
            writer.write(");\n");
        }
        writer.write("default:\nthrow new AssertionError();\n}\n}\n}\n");
        writer.close();
    }
}
