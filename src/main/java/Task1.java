import lambda.Expression;
import utils.Parser;
import utils.Reducer;
import utils.Statics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;

public class Task1 {

    private static final String testPath = "src/test/java/test.in";

    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            throw new IllegalArgumentException("Wrong args: first arg should contain the input path, " +
                    "second arg should contain the path to output file");

        long l = System.currentTimeMillis();

        Expression expression = Parser.parseExpression(args[0]);

        long l1 = System.currentTimeMillis();

        System.out.println("Spent on parsing: " + (l1 - l));

        System.out.println(expression);

        Set<String> allVariables = expression.getAllVariables();

        while (allVariables.contains(Statics.renamingTemplate + Statics.counter.getCount())) {
            Statics.counter.increment();
        }

        Expression reduced = Reducer.reduce(expression);

        System.out.println(reduced);

        try (PrintWriter out = new PrintWriter(new File(args[1]))) {
            out.print(reduced);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Spent on normalization: " + (System.currentTimeMillis() - l1));
    }
}
