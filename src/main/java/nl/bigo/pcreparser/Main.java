package nl.bigo.pcreparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

/**
 * A small demo class that demonstrates how to use the
 * generated parser classes.
 */
public class Main {

    private static final DescriptiveBailErrorListener ERROR_LISTENER = new DescriptiveBailErrorListener();

    private static void displayTokens(String regex) {
        PCRELexer lexer = new PCRELexer(CharStreams.fromString(regex));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill();

        Vocabulary vocabulary = lexer.getVocabulary();

        for (Token token : tokenStream.getTokens()) {
            System.out.printf("%-30s %s\n", token.getText(), vocabulary.getSymbolicName(token.getType()));
        }

        System.out.println();
    }

    private static String stringTree(String regex) {
        PCRELexer lexer = new PCRELexer(CharStreams.fromString(regex));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ERROR_LISTENER);

        PCREParser parser = new PCREParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(ERROR_LISTENER);

        ParseTree tree = parser.pcre();
        StringBuilder builder = new StringBuilder();

        walk(tree, builder);

        return builder.toString();
    }

    private static void walk(ParseTree tree, StringBuilder builder) {

        List<ParseTree> firstStack = new ArrayList<>();
        firstStack.add(tree);

        List<List<ParseTree>> childListStack = new ArrayList<>();
        childListStack.add(firstStack);

        while (!childListStack.isEmpty()) {

            List<ParseTree> childStack = childListStack.get(childListStack.size() - 1);

            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            }
            else {
                tree = childStack.remove(0);

                String node = tree.getClass().getSimpleName().replace("Context", "");
                node = Character.toLowerCase(node.charAt(0)) + node.substring(1);
                StringBuilder indent = new StringBuilder();

                for (int i = 0; i < childListStack.size() - 1; i++) {
                    indent.append((childListStack.get(i).size() > 0) ? "|  " : "   ");
                }

                String tokenName = "";
                int tokenType = node.startsWith("terminal") ? ((CommonToken)tree.getPayload()).getType() : 0;

                if (tokenType > -1) {
                    tokenName = " (" + PCRELexer.VOCABULARY.getSymbolicName(tokenType) + ")";
                }

                builder.append(indent)
                    .append(childStack.isEmpty() ? "'- " : "|- ")
                    .append(node.startsWith("terminal") ? tree.getText() + tokenName : node)
                    .append("\n");

                if (tree.getChildCount() > 0) {
                    List<ParseTree> children = new ArrayList<>();
                    for (int i = 0; i < tree.getChildCount(); i++) {
                        children.add(tree.getChild(i));
                    }
                    childListStack.add(children);
                }
            }
        }
    }

    public static void main(String[] args) {
        String regex = "((.)\\1+ (?<YEAR>(?:19|20)\\d{2})) [^]-x]\\x";

        displayTokens(regex);
        System.out.println(stringTree(regex));
    }
}
