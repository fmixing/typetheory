package utils;

import lambda.Abstraction;
import lambda.Application;
import lambda.Expression;
import lambda.Variable;

import java.util.HashMap;
import java.util.Map;

public class Reducer {

    private static Map<String, Expression> reduceMap = new HashMap<>();

    /**
     * Будем полагаться в этом методе, что все переименования уже выполнены,
     * и у каждой абстракции своя уникальная переменная
     */
    public static Expression reduce(Expression expressionToReduce) {
        if (expressionToReduce instanceof Variable) {
            return expressionToReduce;
        }
        else if (expressionToReduce instanceof Abstraction) {
            return new Abstraction(((Abstraction) expressionToReduce).getVariable(),
                    reduce(((Abstraction) expressionToReduce).getExpression()));
        }
        else {
            Application application = (Application) expressionToReduce;

            Expression expression = headNormalForm(application.getLeft());

            if (expression instanceof Abstraction) {
                return reduce(makeReduction((Abstraction) expression, application.getRight()));
            }
            else {
                return new Application(reduce(expression), reduce(application.getRight()));
            }
        }
    }

    public static Expression headNormalForm(Expression expression) {

        if (expression instanceof Variable) {
            return expression;
        }
        else if (expression instanceof Abstraction) {
            return new Abstraction(((Abstraction) expression).getVariable(),
                    headNormalForm(((Abstraction) expression).getExpression()));
        }
        else {
            Application application = (Application) expression;

            Expression headNormalForm = headNormalForm(application.getLeft());

            if (headNormalForm instanceof Abstraction) {
                return headNormalForm(makeReduction((Abstraction) headNormalForm, application.getRight()));
            }
            else {
                return new Application(headNormalForm, application.getRight());
            }
        }
    }

    private static Expression makeReduction(Abstraction abstraction, Expression toSubstitute) {
//        if (reduceMap.containsKey(abstraction.getVariable().getName())) {
//            return reduceMap.get(abstraction.getVariable().getName());
//        }
//        else {
//            // В (\x.M)y берем M, подставляем в него вместо x y
//            Expression substitute = abstraction.getExpression().substitute(abstraction.getVariable().getName(), toSubstitute);
//            reduceMap.put(abstraction.getVariable().getName(), substitute);
//            return substitute;
//        }
        // В (\x.M)y берем M, подставляем в него вместо x y
        return abstraction.getExpression().substitute(abstraction.getVariable().getName(), toSubstitute);
    }
}
