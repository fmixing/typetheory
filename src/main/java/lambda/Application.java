package lambda;

import utils.NumContainer;

import java.util.Map;
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
    public void getFreeVariables(Set<String> bound, Set<String> free) {
        left.getFreeVariables(bound, free);
        right.getFreeVariables(bound, free);
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
    public Expression alphaConversion(NumContainer count, Map<String, String> renamingMap) {
        Expression newLeft = left.alphaConversion(count, renamingMap);
        Expression newRight = right.alphaConversion(count, renamingMap);
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
    public Expression testRenaming(NumContainer count, Map<String, String> renamingMap) {
        Expression newLeft = left.testRenaming(count, renamingMap);
        Expression newRight = right.testRenaming(count, renamingMap);
        return new Application(newLeft, newRight);
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
