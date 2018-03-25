package lambda;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Variable implements Expression {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public Set<String> getFreeVariables() {
        HashSet<String> singleton = new HashSet<>();
        singleton.add(name);
        return singleton;
    }

    @Override
    public Set<String> getAllVariables() {
        HashSet<String> singleton = new HashSet<>();
        singleton.add(name);
        return singleton;
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
