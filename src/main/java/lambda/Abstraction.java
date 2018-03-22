package lambda;

import utils.NumContainer;
import utils.Statics;

import java.util.Map;
import java.util.Set;

public class Abstraction implements Expression {

    private final Variable variable;

    private final Expression expression;

    public Abstraction(Variable variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void getFreeVariables(Set<String> bound, Set<String> free) {
        boolean add = bound.add(variable.getName());
        expression.getFreeVariables(bound, free);
        if (add) {
            bound.remove(variable.getName());
        }
    }

    @Override
    public boolean hasBetaRedex() {
        return expression.hasBetaRedex();
    }

    @Override
    public Expression substitute(String nameToChange, Expression substitution) {
        // Если выражение вида \x.y x, и x мы хотим заменить на что-то, то заменять в данном выражении ничего не надо
        if (variable.getName().equals(nameToChange)) {
            return new Abstraction(variable, expression);
        }
        Expression newExpression = expression.substitute(nameToChange, substitution);
        return new Abstraction(variable, newExpression);
    }

    @Override
    public Abstraction cloneExpression() {
        return new Abstraction(variable.cloneExpression(), expression.cloneExpression());
    }

    @Override
    public Expression rename(String nameToChange, String newName) {
        if (variable.getName().equals(nameToChange)) {
            return new Abstraction(new Variable(newName), expression.rename(nameToChange, newName));
        }
        return new Abstraction(variable.cloneExpression(), expression.rename(nameToChange, newName));
    }

    @Override
    public Expression alphaConversion(NumContainer count, Map<String, String> renamingMap) {
        int currCount = count.increment();

        // здесь уже все связанные переменные переименованы каждый в свое уникальное имя
        Expression renamed = expression.alphaConversion(count, renamingMap);
        String newName = Statics.renamingTemplate + currCount;
        // теперь можно просто заменить имя переменной этой абстракции на новое имя
        Expression allRenamed = renamed.rename(variable.getName(), newName);

        // по новому уникальному имени мы хотим уметь находить старое
        renamingMap.put(newName, variable.getName());

        return new Abstraction(new Variable(newName), allRenamed);
    }

    @Override
    public Expression renameBack(Map<String, String> renamingMap) {
        // Очевидно, что в процессе нормализации новые абстракции не могли появиться (или не очевидно, это мы посмотрим)
        assert renamingMap.containsKey(variable.getName());

        Expression expression = this.expression.renameBack(renamingMap);
        return new Abstraction(new Variable(renamingMap.get(variable.getName())), expression);
    }

    @Override
    public String toString() {
        return "(" + "\\" + variable + "." + expression + ")";
    }

    public Variable getVariable() {
        return variable;
    }

    public Expression getExpression() {
        return expression;
    }
}
