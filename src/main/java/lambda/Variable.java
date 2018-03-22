package lambda;

import utils.NumContainer;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Variable implements Expression {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public void getFreeVariables(Set<String> bound, Set<String> free) {
        if (!bound.contains(name)) {
            free.add(name);
        }
    }

    @Override
    public boolean hasBetaRedex() {
        return false;
    }

    @Override
    public Expression substitute(String nameToChange, Expression substitution) {
        if (name.equals(nameToChange)) {
            return substitution.cloneExpression();
        }
        return cloneExpression();
    }

    @Override
    public Variable cloneExpression() {
        return this;
    }

    @Override
    public Expression rename(String nameToChange, String newName) {
        if (name.equals(nameToChange)) {
            return new Variable(newName);
        }
        return cloneExpression();
    }

    @Override
    public Expression alphaConversion(NumContainer count, Map<String, String> renamingMap) {
        return cloneExpression();
    }

    @Override
    public Expression renameBack(Map<String, String> renamingMap) {
        if (renamingMap.containsKey(name)) {
            return new Variable(renamingMap.get(name));
        }
        return cloneExpression();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
