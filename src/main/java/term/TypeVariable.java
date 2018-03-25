package term;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TypeVariable implements Term {

    private final String name;

    public TypeVariable(String name) {
        this.name = name;
    }

    @Override
    public boolean containsVariable(String name) {
        return this.name.equals(name);
    }

    @Override
    public Term substitute(String name, Term termToSubstitute) {
        if (!this.name.equals(name)) {
           return this;
        }
        return termToSubstitute;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeVariable that = (TypeVariable) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
