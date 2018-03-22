package lambda;

import utils.NumContainer;

import java.util.Map;
import java.util.Set;

public interface Expression {

    void getFreeVariables(Set<String> bound, Set<String> free);

    boolean hasBetaRedex();

    /**
     * Нужен для бета-редукции, то есть подставления вместо переменной выражения
     */
    Expression substitute(String nameToChange, Expression substitution);

    Expression cloneExpression();

    Expression rename(String nameToChange, String newName);

    /**
     * Метод для переименования связных переменных в новые уникальные имена (то есть \x.\x.x -> \nv1.\nv2.nv2)
     */
    Expression alphaConversion(NumContainer count, Map<String, String> renamingMap);

    /**
     * Метод для обратного переименования
     *
     * для ускорения можно добавить лайфхаки типа возвращения (expr, bool), в которой хранить,
     * встречалась ли переменная для переименования внутри текущей абстракции
     */
    Expression renameBack(Map<String, String> renamingMap);
}
