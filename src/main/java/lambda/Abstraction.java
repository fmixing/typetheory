package lambda;

import utils.NumContainer;
import utils.Statics;

import java.util.HashSet;
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
        HashSet<String> free = new HashSet<>();
        expression.getFreeVariables(new HashSet<>(), free);

        if (variable.getName().equals(nameToChange) || !free.contains(nameToChange)) {
            return cloneExpression();
        }
        Expression newExpression = expression.substitute(nameToChange, substitution);
        return new Abstraction(variable, newExpression);
    }

    @Override
    public Abstraction cloneExpression() {
        return this;
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
    public Expression testRenaming(NumContainer count, Map<String, String> renamingMap) {
        if (renamingMap.containsKey(variable.getName())) {
            Expression expression = this.expression.testRenaming(count, renamingMap);

            return new Abstraction(new Variable(renamingMap.get(variable.getName())), expression);
        }
        else {
            int currCount = count.increment();

            String newName = Statics.renamingTemplate + currCount;

            renamingMap.put(variable.getName(), newName);

            Expression expression = this.expression.testRenaming(count, renamingMap);

            return new Abstraction(new Variable(newName), expression);
        }
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
