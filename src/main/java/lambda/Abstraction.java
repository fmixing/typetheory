package lambda;

import utils.Statics;

import java.util.Set;

public class Abstraction implements Expression {

    private final Variable variable;

    private final Expression expression;

    public Abstraction(Variable variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public Set<String> getFreeVariables() {
        Set<String> freeVariables = expression.getFreeVariables();
        freeVariables.remove(variable.getName());
        return freeVariables;
    }

    @Override
    public Set<String> getAllVariables() {
        Set<String> allVariables = expression.getAllVariables();
        allVariables.add(variable.getName());
        return allVariables;
    }

    @Override
    public boolean hasBetaRedex() {
        return expression.hasBetaRedex();
    }

    @Override
    public Expression substitute(String nameToChange, Expression substitution) {
        Set<String> free = expression.getFreeVariables();

        // Если выражение вида \x.y x, и x мы хотим заменить на что-то, то заменять в данном выражении ничего не надо.
        // Также заменять не надо, если переменная на замену не содержится в теле абстракции в виде свободной переменной
        if (variable.getName().equals(nameToChange) || !free.contains(nameToChange)) {
            return cloneExpression();
        }

        Set<String> freeVariables = substitution.getFreeVariables();

        // Если текущая абстракция не связывает выражение для подстановки, то просто спускаемся внутрь
        if (!freeVariables.contains(variable.getName())) {
            return new Abstraction(variable, expression.substitute(nameToChange, substitution));
        }

        // В текущий момент имя абстракции содержится в подстановке => переменная станет связной.
        // Заменим все вхождения имени на новое имя

        String newName = Statics.renamingTemplate + Statics.counter.increment();

        Expression rename = expression.rename(variable.getName(), newName);

        // Теперь для внутри абстракции произошла замена имени абстракции на новое уникальное имя,
        // можем произвести подстановку

        Expression newExpression = rename.substitute(nameToChange, substitution);
        return new Abstraction(new Variable(newName), newExpression);
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
