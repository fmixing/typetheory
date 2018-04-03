package term;

import java.util.Objects;
import java.util.Set;

public class Forall implements Term {

    private final TypeVariable variable;

    private final Term term;

    public Forall(TypeVariable variable, Term term) {
        this.variable = variable;
        this.term = term;
    }

    @Override
    public boolean containsVariable(String name) {
        return !variable.getName().equals(name) && term.containsVariable(name);
    }

    @Override
    public Term substitute(String name, Term termToSubstitute) {
        if (variable.getName().equals(name)) {
            return term.substitute(name, termToSubstitute);
//            return this;
        }

        return new Forall(variable, term.substitute(name, termToSubstitute));
    }

    @Override
    public Set<String> getFreeVariables() {
        Set<String> freeVariables = term.getFreeVariables();
        freeVariables.remove(variable.getName());
        return freeVariables;
    }

    public TypeVariable getVariable() {
        return variable;
    }

    public Term getTerm() {
        return term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forall forall = (Forall) o;
        return Objects.equals(variable, forall.variable) &&
                Objects.equals(term, forall.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, term);
    }

    @Override
    public String toString() {
        return "@" + variable + "." + term;
    }
}
