package utils;

import lambda.*;
import term.*;

import java.util.*;

public class TypeInferencerLet {

    public static Optional<Term> inferType(Map<Variable, Term> gamma, Expression expression) {
        expression.getFreeVariables().forEach(freeVar -> gamma.put(new Variable(freeVar), new TypeVariable(getNewName())));

        ArrayList<Pair<TypeVariable, Term>> substitution = new ArrayList<>();

        Optional<Term> w = W(expression, gamma, substitution);
        makeSubstitutions(gamma, substitution);

        return w;
    }

    /**
     * @param gamma - контекст, содержащий типы переменных, входящих в выражение
     * @param substitution - итоговая подстановка
     * @return тип выражения
     */
    private static Optional<Term> W(Expression expression, Map<Variable, Term> gamma, List<Pair<TypeVariable, Term>> substitution) {
        if (expression instanceof Variable) {
            Term term = gamma.get(expression);

            if (term == null) {
                return Optional.empty();
            }

            Set<String> quantifiedNames = getQuantifiedNames(term);

            for (String name : quantifiedNames) {
                term = term.substitute(name, new TypeVariable(getNewName()));
            }

            return Optional.of(term);
        }
        else if (expression instanceof Application) {
            // Находим тип и подстановку для левого выражения
            ArrayList<Pair<TypeVariable, Term>> s1 = new ArrayList<>();

            Optional<Term> t1 = W(((Application) expression).getLeft(), gamma, s1);

            // Исправляем контекст на контекст с подстановкой
            makeSubstitutions(gamma, s1);

            // Находим тип и подстановку для правого выражения с измененным контекстом
            ArrayList<Pair<TypeVariable, Term>> s2 = new ArrayList<>();

            Optional<Term> t2 = W(((Application) expression).getRight(), gamma, s2);

            if (!t1.isPresent() || !t2.isPresent()) {
                return Optional.empty();
            }

            // Подставляем в тип левого выражения подстановку из правого
            Term t1Substituted = makeSubstitutions(t1.get(), s2);

            TypeVariable type = new TypeVariable(getNewName());

            Implication implication = new Implication(t2.get(), type);

            // Решаем уравнение
            Optional<List<Pair<TypeVariable, Term>>> maybeUnify = unify(new Equation(t1Substituted, implication));

            if (!maybeUnify.isPresent()) {
                return Optional.empty();
            }

            List<Pair<TypeVariable, Term>> unify = maybeUnify.get();

            // Подставляем в левую подстановку правую
            List<Pair<TypeVariable, Term>> composed = makeComposition(s1, s2);

            // Подставляем в итоговую подстановку унификацию
            substitution.addAll(makeComposition(composed, unify));

            // Подставляем в итоговый тип подстановку
            return Optional.of(makeSubstitutions(type, unify));
        }
        else if (expression instanceof Abstraction) {
            Variable variable = ((Abstraction) expression).getVariable();

            TypeVariable newType = new TypeVariable(getNewName());

            Term variableType = gamma.put(variable, newType);

            Optional<Term> type = W(((Abstraction) expression).getExpression(), gamma, substitution);

            if (variableType != null) {
                gamma.put(variable, variableType);
            }
            else {
                gamma.remove(variable);
            }

            return type.map(term -> new Implication(makeSubstitutions(newType, substitution), term));
        }
        else {
            Let let = (Let) expression;

            List<Pair<TypeVariable, Term>> s1 = new ArrayList<>();

            Optional<Term> t1 = W(let.getLet(), gamma, s1);

            if (!t1.isPresent()) {
                return t1;
            }

            gamma.remove(let.getVariable());

            makeSubstitutions(gamma, s1);

            Term closedLetType = closeType(gamma, t1.get());

            Term variableType = gamma.put(let.getVariable(), closedLetType);

            List<Pair<TypeVariable, Term>> s2 = new ArrayList<>();

            Optional<Term> t2 = W(let.getIn(), gamma, s2);

            substitution.addAll(makeComposition(s1, s2));

            if (variableType != null) {
                gamma.put(((Let) expression).getVariable(), variableType);
            }
            else {
                gamma.remove(((Let) expression).getVariable());
            }

            return t2;
        }
    }

    private static Term closeType(Map<Variable, Term> gamma, Term letType) {
        Set<String> freeVariables = letType.getFreeVariables();

        for (String varName : freeVariables) {
            if (gamma.isEmpty()) {
                letType = new Forall(new TypeVariable(varName), letType);
                continue;
            }

            for (Map.Entry<Variable, Term> entry : gamma.entrySet()) {
                if (!entry.getValue().getFreeVariables().contains(varName)) {
                    letType = new Forall(new TypeVariable(varName), letType);
                }
            }
        }

        return letType;
    }

    /**
     * Left o Right
     * @param right - куда подставляем
     * @param left - что подставляем
     */
    private static List<Pair<TypeVariable, Term>> makeComposition(List<Pair<TypeVariable, Term>> right,
                                                                  List<Pair<TypeVariable, Term>> left) {
        for (Pair<TypeVariable, Term> entry : left) {
            right.forEach(pair -> {
                Term substitute = pair.getValue().substitute(entry.getKey().getName(), entry.getValue());
                pair.setValue(substitute);
            });
        }
        right.addAll(left);
        return right;
    }

    private static Optional<List<Pair<TypeVariable, Term>>> unify(Equation equation) {
        HashSet<Equation> equations = new HashSet<>();
        equations.add(equation);
        Optional<Term> a = TypeInferencer.solveEquationSystem(new TypeVariable("a"), equations);

        if (!a.isPresent()) {
            return Optional.empty();
        }

        List<Pair<TypeVariable, Term>> unification = new ArrayList<>();

        equations.forEach(currEquation -> {
            if (!(currEquation.getLeft() instanceof TypeVariable)) {
                throw new RuntimeException(currEquation.getLeft().toString());
            }

            unification.add(new Pair<>((TypeVariable) currEquation.getLeft(), currEquation.getRight()));
        });

        return Optional.of(unification);
    }

    private static Term makeSubstitutions(Term type, List<Pair<TypeVariable, Term>> substitution) {
        for (Pair<TypeVariable, Term> entry : substitution) {
            type = type.substitute(entry.getKey().getName(), entry.getValue());
        }
        return type;
    }

    private static void makeSubstitutions(Map<Variable, Term> gamma, List<Pair<TypeVariable, Term>> substitution) {
        for (Pair<TypeVariable, Term> entry : substitution) {
            gamma.replaceAll((k, v) -> v.substitute(entry.getKey().getName(), entry.getValue()));
        }
    }

    private static Set<String> getQuantifiedNames(Term term) {
        if (term instanceof Forall) {
            Set<String> quantifiedNames = getQuantifiedNames(((Forall) term).getTerm());
            quantifiedNames.add(((Forall) term).getVariable().getName());
            return quantifiedNames;
        }
        else {
            return new HashSet<>();
        }
    }

    private static String getNewName() {
        return Statics.typeNameTemplate + Statics.counter.increment();
    }

    private static class Pair <K, V> {
        K key;

        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        void setKey(K key) {
            this.key = key;
        }

        V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }
    }
}
