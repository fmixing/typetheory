package term;

import java.util.Objects;
import java.util.Set;

public class Equation {

    private final Term left;

    private final Term right;

    public Equation(Term left, Term right) {
        this.left = left;
        this.right = right;
    }

    public boolean containsVariable(String name) {
        return left.containsVariable(name) || right.containsVariable(name);
    }

    public Equation substitute(String name, Term termToSubstitute) {
        return new Equation(left.substitute(name, termToSubstitute), right.substitute(name, termToSubstitute));
    }

    public Set<String> getFreeVariables() {
        Set<String> freeVariables = left.getFreeVariables();
        freeVariables.addAll(right.getFreeVariables());
        return freeVariables;
    }

    public Term getLeft() {
        return left;
    }

    public Term getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left + " = " + right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return Objects.equals(left, equation.left) &&
                Objects.equals(right, equation.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
