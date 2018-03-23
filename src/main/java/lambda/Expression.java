package lambda;

import java.util.Set;

public interface Expression {

    Set<String> getFreeVariables();

    Set<String> getBoundVariables();

    /**
     * Нужен для получения нового уникального имени
     */
    Set<String> getAllVariables();

    boolean hasBetaRedex();

    /**
     * Нужен для бета-редукции, то есть подставления вместо переменной выражения
     */
    Expression substitute(String nameToChange, Expression substitution);

    Expression cloneExpression();

    Expression rename(String nameToChange, String newName);
}
