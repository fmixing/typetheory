package lambda;

import java.util.Objects;
import java.util.Set;

public class Application implements Expression {

    private final Expression left;

    private final Expression right;

    public Application(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + " " + right + ")";
    }

    @Override
    public Set<String> getFreeVariables() {
        Set<String> freeVariables = left.getFreeVariables();
        freeVariables.addAll(right.getFreeVariables());
        return freeVariables;
    }

    @Override
    public Set<String> getAllVariables() {
        Set<String> allVariables = left.getAllVariables();
        allVariables.addAll(right.getAllVariables());
        return allVariables;
    }

    @Override
    public boolean hasBetaRedex() {
        return left instanceof Abstraction;
    }

    @Override
    public Expression substitute(String nameToChange, Expression substitution) {
        Expression newLeft = left.substitute(nameToChange, substitution);
        Expression newRight = right.substitute(nameToChange, substitution);

        return new Application(newLeft, newRight);
    }

    @Override
    public Application cloneExpression() {
        return this;
    }

    @Override
    public Expression rename(String nameToChange, String newName) {
        return new Application(left.rename(nameToChange, newName), right.rename(nameToChange, newName));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
