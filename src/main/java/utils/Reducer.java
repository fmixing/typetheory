package utils;

import lambda.Abstraction;
import lambda.Application;
import lambda.Expression;
import lambda.Variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Reducer {

    private static Map<Expression, Expression> reduceMap = new HashMap<>();

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
//                Expression substitute = reduceMap.get(application);
//
//                if (substitute != null) {
//                    return substitute;
//                }

                Expression substitute = makeReduction((Abstraction) expression, application.getRight());

//                reduceMap.put(application, substitute);

                return reduce(substitute);
            }
            else {
                return new Application(reduce(expression), reduce(application.getRight()));
            }
        }
    }

    private static Expression headNormalForm(Expression expression) {
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
                Expression substitute = reduceMap.get(application);

                if (substitute != null) {
                    return substitute;
                }

                substitute = headNormalForm(makeReduction((Abstraction) headNormalForm, application.getRight()));

                reduceMap.put(application, substitute);

                return substitute;
            }
            else {
                return new Application(headNormalForm, application.getRight());
            }
        }
    }

    private static Expression makeReduction(Abstraction abstraction, Expression toSubstitute) {
        // В (\x.M)y берем M, подставляем в него вместо x y
        return abstraction.getExpression().substitute(abstraction.getVariable().getName(), toSubstitute);
    }
}
