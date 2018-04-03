package term;

import java.util.Set;

public interface Term {
    boolean containsVariable(String name);

    Term substitute(String name, Term termToSubstitute);

    Set<String> getFreeVariables();
}
