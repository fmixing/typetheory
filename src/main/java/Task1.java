import lambda.Expression;
import utils.NumContainer;
import utils.Parser;
import utils.Reducer;

import java.util.HashMap;

public class Task1 {

    private static final String testPath = "src/test/java/test.in";

    public static void main(String[] args) {

        long l = System.currentTimeMillis();

        Expression expression = Parser.parseExpression(testPath);

        long l1 = System.currentTimeMillis();

        System.out.println("Spent on parsing: " + (l1 - l));

//        System.out.println(expression);

        Expression expression1 = expression.alphaConversion(new NumContainer(), new HashMap<>());

        System.out.println(expression1);

        Expression reduced = Reducer.headNormalForm(expression1);

        System.out.println(reduced);

        System.out.println("Spent on normalization: " + (System.currentTimeMillis() - l1));
    }
}
