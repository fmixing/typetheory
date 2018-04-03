package lambda;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Let implements Expression {

    private final Variable variable;

    private final Expression let;

    private final Expression in;

    public Let(Variable variable, Expression let, Expression in) {
        this.variable = variable;
        this.let = let;
        this.in = in;
    }

    @Override
    public Set<String> getFreeVariables() {
        Set<String> freeVariables = let.getFreeVariables();
        freeVariables.addAll(in.getFreeVariables());
        return freeVariables;
    }

    @Override
    public Set<String> getAllVariables() {
        Set<String> allVariables = let.getAllVariables();
        allVariables.addAll(in.getAllVariables());
        return allVariables;
    }

    @Override
    public boolean hasBetaRedex() {
        return let.hasBetaRedex() || in.hasBetaRedex();
    }

    @Override
    public Expression substitute(String nameToChange, Expression substitution) {
        //todo
        return null;
    }

    @Override
    public Expression cloneExpression() {
        return this;
    }

    @Override
    public Expression rename(String nameToChange, String newName) {
        //todo
        return null;
    }

    public Variable getVariable() {
        return variable;
    }

    public Expression getLet() {
        return let;
    }

    public Expression getIn() {
        return in;
    }

    @Override
    public String toString() {
        return "let " + variable + "=" + let + " in " + in;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Let let1 = (Let) o;
        return Objects.equals(variable, let1.variable) &&
                Objects.equals(let, let1.let) &&
                Objects.equals(in, let1.in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, let, in);
    }
}
