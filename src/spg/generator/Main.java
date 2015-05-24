package spg.generator;

import spg.generator.parser.GrammarParser;
import spg.generator.structure.Grammar;
import spg.runtime.ParseException;
import spg.runtime.Table;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Unexpected number of arguments!");
        } else {
            try {
                Grammar grammar = GrammarParser.parse(args[0]);
                Table table = TableGenerator.generateTable(grammar);
                ClassGenerator.generateNonTerminals(grammar);
                ConfigurationGenerator.generateConfiguration(grammar, table);
            } catch (FileNotFoundException e) {
                System.err.println("Error while reading file: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error while generating files: " + e.getMessage());
            } catch (GenerationException e) {
                System.err.println("Conflict during table generation!");
            } catch (ParseException e) {
                System.err.println("Error while parsing grammar: " + e.getMessage());
            }
        }
    }
}
