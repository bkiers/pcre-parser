package nl.bigo.pcreparser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

/**
 * A small demo class that demonstrates how to use the
 * generated parser classes.
 */
public class Main {

    public static String toStringASCII(ParseTree tree) {

        StringBuilder builder = new StringBuilder();
        walk(tree, builder);

        return builder.toString();
    }

    @SuppressWarnings("unchecked")
    private static void walk(ParseTree tree, StringBuilder builder) {

        List<ParseTree> firstStack = new ArrayList<ParseTree>();
        firstStack.add(tree);

        List<List<ParseTree>> childListStack = new ArrayList<List<ParseTree>>();
        childListStack.add(firstStack);

        while (!childListStack.isEmpty()) {

            List<ParseTree> childStack = childListStack.get(childListStack.size() - 1);

            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            }
            else {
                tree = childStack.remove(0);

                String indent = "";

                for (int i = 0; i < childListStack.size() - 1; i++) {
                    indent += (childListStack.get(i).size() > 0) ? "|  " : "   ";
                }

                builder.append(indent)
                        .append(childStack.isEmpty() ? "'- " : "|- ")
                        .append(tree.getText())
                        .append("\n");

                if (tree.getChildCount() > 0) {
                    List<ParseTree> children = new ArrayList<ParseTree>();
                    for (int i = 0; i < tree.getChildCount(); i++) {
                        children.add(tree.getChild(i));
                    }
                    childListStack.add(children);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
//      System.err.println("Usage: java -jar pcre-parser-0.1.0.jar \"input to parse\"");
//      System.exit(1);
            args = new String[]{"((.)\\1+ (?<YEAR>(?:19|20)\\d{2})) [^]-x]"};
        }

        PCRELexer lexer = new PCRELexer(new ANTLRInputStream(args[0]));
        PCREParser parser = new PCREParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();

        System.out.println(toStringASCII(tree));
    }
}
