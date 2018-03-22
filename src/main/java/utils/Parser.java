package utils;

import grammar.LambdaLexer;
import grammar.LambdaParser;
import lambda.Expression;
import org.antlr.v4.runtime.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public static Expression parseExpression(String inPath) {
        try (Scanner in = new Scanner(new File(inPath))) {
            if (in.hasNextLine()) {
                String expression = in.nextLine();

                return parseInternal(expression);
            }
            else {
                throw new RuntimeException("File is empty");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Expression> parseExpressions(String inPath) {
        List<Expression> expressions = new ArrayList<>();

        try (Scanner in = new Scanner(new File(inPath))) {
            while (in.hasNextLine()) {
                String expression = in.nextLine();

                expressions.add(parseInternal(expression));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return expressions;
    }

    private static Expression parseInternal(String expression) {
        CodePointCharStream is = CharStreams.fromString(expression);
        LambdaLexer lexer = new LambdaLexer(is);
        TokenStream ts = new CommonTokenStream(lexer);
        LambdaParser parser = new LambdaParser(ts);
        return parser.expression().expr;
    }
}
