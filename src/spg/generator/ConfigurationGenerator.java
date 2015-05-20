package spg.generator;

import spg.generator.structure.Grammar;
import spg.runtime.Table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfigurationGenerator {
    public static void generateConfiguration(Grammar grammar, Table table) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(grammar.prefix + "/ConfigurationImpl.java")));
        writer.write("package " + grammar.pkg + ";\nimport spg.runtime.*;\nimport java.util.List;\n" +
                        "import java.util.ArrayList;\npublic class ConfigurationImpl implements Configuration{\n" +
                        "private int[]l=new int[]{"
        );
        for (int i = 0; i < grammar.rules.size(); i++) {
            writer.write(grammar.rules.get(i).rule.size() + (i == grammar.rules.size() - 1 ? "" : ","));
        }
        writer.write("};\nprivate Action[][]a=new Action[][]{");
        for (int i = 0; i < table.actions.size(); i++) {
            writer.write("new Action[]{");
            for (int j = 0; j < table.actions.get(i).size(); j++) {
                writer.write("Action." + table.actions.get(i).get(j) + (j == table.actions.get(i).size() - 1 ? "" : ","));
            }
            writer.write("}" + (i == table.actions.size() - 1 ? "" : ","));
        }
        writer.write("};\nprivate int[][]n=new int[][]{");
        for (int i = 0; i < table.next.size(); i++) {
            writer.write("new int[]{");
            for (int j = 0; j < table.next.get(i).size(); j++) {
                writer.write(table.next.get(i).get(j) + (j == table.next.get(i).size() - 1 ? "" : ","));
            }
            writer.write("}" + (i == table.next.size() - 1 ? "" : ","));
        }
        writer.write("};\npublic String[]v=new String[]{");
        for (int i = 0; i < grammar.terminals.size(); i++) {
            writer.write("\"" + grammar.terminals.get(i).value + "\"" + (i == grammar.terminals.size() - 1 ? "" : ","));
        }
        writer.write("};\npublic boolean[]g=new boolean[]{");
        for (int i = 0; i < grammar.terminals.size(); i++) {
            writer.write(grammar.terminals.get(i).ignore + (i == grammar.terminals.size() - 1 ? "" : ","));
        }
        writer.write("};\npublic int getLength(int n){\nreturn l[n];\n}\npublic RuleManager getManager(){\n" +
                        "return new RuleManagerImpl();\n}\npublic Table getTable(){\n" +
                        "List<List<Action>>ac=new ArrayList<>();List<List<Integer>>nx=new ArrayList<>();\n" +
                        "for(int i=0;i<a.length;i++){\nac.add(new ArrayList<>());\nnx.add(new ArrayList<>());\n" +
                        "for(int j=0;j<a[i].length;j++){\nac.get(ac.size()-1).add(a[i][j]);\n" +
                        "nx.get(nx.size()-1).add(n[i][j]);\n}\n}\nreturn new Table(ac,nx);\n}\n" +
                        "public int getTerminalNumber(){\nreturn " + grammar.terminals.size() + ";\n}\n" +
                        "public int getNonTerminalNumber(){\nreturn " + grammar.nonTerminals.size() + ";\n}\n" +
                        "public List<String> getValues(){\nList<String>va=new ArrayList<>();\n" +
                        "for(int i=0;i<v.length;i++){\nva.add(v[i]);\n}\nreturn va;\n}\n" +
                        "public List<Boolean>getIgnore(){\nList<Boolean>ig=new ArrayList<>();\n" +
                        "for(int i=0;i<g.length;i++){\nig.add(g[i]);\n}\nreturn ig;\n}\n}\n"
        );
        writer.close();
    }
}
