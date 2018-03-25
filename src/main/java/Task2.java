import lambda.Expression;
import lambda.Variable;
import term.Term;
import utils.Parser;
import utils.TypeInferencer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Task2 {

    private static final String testPath = "src/test/java/test2.in";

    public static void main(String[] args) {
        if (args.length != 2 || args[0] == null || args[1] == null)
            throw new IllegalArgumentException("Wrong args: first arg should contain the input path, " +
                    "second arg should contain the path to output file");

        Map<Expression, Term> types = new HashMap<>();

        long l = System.currentTimeMillis();

        Expression expression = Parser.parseExpression(args[0]);

        long l1 = System.currentTimeMillis();

        System.out.println("Spent on parsing: " + (l1 - l));

        System.out.println(expression);

        Optional<Term> term = TypeInferencer.inferType(types, expression);

        if (!term.isPresent()) {
            System.out.println("Лямбда-выражение не имеет типа.");
            return;
        }

        Term type = term.get();

        System.out.println();

        try (PrintWriter out = new PrintWriter(new File(args[1]))) {
            out.print(type);
            types.forEach((k, v) -> {
                if (k instanceof Variable) {
                    out.println(k + " : " + v);
                }
            });

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        System.err.println(type);

        types.forEach((k, v) -> System.err.println(k + " : " + v));

        System.out.println("Spent on type inference: " + (System.currentTimeMillis() - l1));
    }
}
