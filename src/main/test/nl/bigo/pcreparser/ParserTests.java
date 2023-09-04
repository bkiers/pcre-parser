package nl.bigo.pcreparser;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserTests {

    private static PCREParser parser(String source) {
        PCRELexer lexer = new PCRELexer(CharStreams.fromString(source));
        PCREParser parser = new PCREParser(new CommonTokenStream(lexer));

        parser.removeErrorListeners();
        parser.setErrorHandler(new BailErrorStrategy());

        return parser;
    }

    private static String lastRule(ParseTree root) {
        InternalNodeListener listener = new InternalNodeListener();
        ParseTreeWalker.DEFAULT.walk(listener, root);
        return listener.lastRule();
    }

    @Test
    @SuppressWarnings("all")
    public void parseFiles() throws IOException {
        int errors = 0;
        int line = 0;

        for (File file : new File("src/resources").listFiles((dir, name) -> name.endsWith(".txt"))) {

            String fileName = file.getName().split("\\.")[0];
            line = 0;

            try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
                for (String pcreLine : stream.collect(Collectors.toList())) {

                    String pcre = pcreLine.trim();
                    line++;

                    if (pcre.isEmpty() || pcre.startsWith("#")) {
                        // Skip empty lines or lines starting with a '#'
                        continue;
                    }

                    try {
                        ParseTree root = parser(pcre).pcre();
                        String lastRule = lastRule(root);

                        if (!lastRule.equals(fileName) && !fileName.startsWith("@")) {
                            System.err.printf("INCORRECT: could not parse line %d, `%s`, in file %s as a `%s` (got a `%s`)%n",
                                    line, pcre, file.getAbsolutePath(), fileName, lastRule);
                            errors++;
                        }

                        // System.out.printf("OK: `%s` parsed as a `%s`%n", pcre, lastRule);
                    }
                    catch (Exception e) {
                        errors++;
                        System.err.printf("ERROR: could not parse line %d, `%s`, in file %s: %s%n",
                                line, pcre, file.getAbsolutePath(), e.getMessage());
                    }
                }
            }

            if (errors == 0) {
                System.out.printf("OK: %s%n", file.getAbsolutePath());
            }
        }

        Assert.assertEquals(0, errors);
    }
}
