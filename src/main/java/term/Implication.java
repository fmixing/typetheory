package term;

import java.util.Objects;

public class Implication implements Term {

    private final Term left;

    private final Term right;

    public Implication(Term left, Term right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean containsVariable(String name) {
        return left.containsVariable(name) || right.containsVariable(name);
    }

    @Override
    public Term substitute(String name, Term termToSubstitute) {
        return new Implication(left.substitute(name, termToSubstitute), right.substitute(name, termToSubstitute));
    }

    public Term getLeft() {
        return left;
    }

    public Term getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left + " -> " + right + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Implication that = (Implication) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
