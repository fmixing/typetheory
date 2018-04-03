package utils;

import lambda.Abstraction;
import lambda.Application;
import lambda.Expression;
import lambda.Variable;
import term.Equation;
import term.Implication;
import term.Term;
import term.TypeVariable;

import java.util.*;

public class TypeInferencer {

    public static Optional<Term> inferType(Map<Expression, Term> types, Expression expression) {
        Map<String, Term> nameToType = new HashMap<>();
        Set<Equation> equations = new HashSet<>();
        Term type = createEquationSystem(types, nameToType, equations, expression);
        Optional<Term> maybeType = solveEquationSystem(type, equations);
        return maybeType.flatMap(term -> substituteToType(term, types, equations));
    }

    /**
     * @param types – мапа из выражения в его тип
     * @param nameToType – мапа из типовой переменной в ее тип
     * @param equations - система уравнений, которая выводится рекурсивно
     * @param expression – выражение
     */
    private static Term createEquationSystem(Map<Expression, Term> types, Map<String, Term> nameToType,
                                             Set<Equation> equations, Expression expression) {
        if (expression instanceof Variable) {
            String name = ((Variable) expression).getName();
            Term variableType;

            if (!nameToType.containsKey(name)) {
                variableType = new TypeVariable(getNewName());
                types.put(expression, variableType);
            }
            else {
                variableType = nameToType.get(name);
            }

            return variableType;
        }
        else if (expression instanceof Application) {
            Term leftType = createEquationSystem(types, nameToType, equations, ((Application) expression).getLeft());
            Term rightType = createEquationSystem(types, nameToType, equations, ((Application) expression).getRight());

            TypeVariable typeVariable = new TypeVariable(getNewName());
            equations.add(new Equation(leftType, new Implication(rightType, typeVariable)));
            types.put(expression, typeVariable);

            return typeVariable;
        }
        else {
            String name = ((Abstraction) expression).getVariable().getName();

            Term prevVariableType = nameToType.get(name);

            TypeVariable newVariableType = new TypeVariable(getNewName());

            nameToType.put(name, newVariableType);

            Term expressionType = createEquationSystem(types, nameToType, equations, ((Abstraction) expression).getExpression());

            if (prevVariableType != null) {
                nameToType.put(name, prevVariableType);
            }
            else {
                nameToType.remove(name);
            }

            Implication implication = new Implication(newVariableType, expressionType);
            types.put(expression, implication);

            return implication;
        }
    }

    static Optional<Term> solveEquationSystem(Term type, Set<Equation> equations) {
        Set<Equation> prevEquations = new HashSet<>(equations);
        Set<Equation> currEquations = new HashSet<>();

        while (true) {
            boolean changed = false;

            for (Equation equation : prevEquations) {
                Term left = equation.getLeft();
                Term right = equation.getRight();

                // Не добавляем в новый набор уравнений a = a
                if (left.equals(right)) {
                    changed = true;
                    continue;
                }

                // Для T = x меняем местами на x = T
                if (toXEqualsT(left, right, equation, currEquations)) {
                    changed = true;
                    continue;
                }

                // Для f -> g = k -> m сделали f = k, g = m
                if (parseImplication(left, right, equation, currEquations)) {
                    changed = true;
                    continue;
                }

                // Если x = T, и T содержит x, то тип нельзя вывести
                if (left instanceof TypeVariable) {
                    if (right.containsVariable(((TypeVariable) left).getName())) {
                        return Optional.empty();
                    }
                }

                currEquations.add(equation);
            }

            // Теперь сделаем необходимые подстановки
            changed |= substitute(currEquations);

            if (!changed) {
                break;
            }
            
            drainTo(prevEquations, currEquations);
        }
        equations.clear();
        equations.addAll(currEquations);

        return Optional.of(type);
    }

    private static Optional<Term> substituteToType(Term type, Map<Expression, Term> types, Set<Equation> currEquations) {
        // Теперь в currEquations содержатся все необходимые уравнения вида x = T. Нужно подставить их
        currEquations.forEach(equation -> {
            if (!(equation.getLeft() instanceof TypeVariable)) {
                throw new RuntimeException(equation.getLeft().toString());
            }
        });

        Term term = typeSubstitute(type, currEquations);

        types.replaceAll((k, v) -> typeSubstitute(v, currEquations));

        return Optional.of(term);
    }

    private static boolean parseImplication(Term left, Term right, Equation equation, Set<Equation> currEquations) {
        if (left instanceof Implication && right instanceof Implication) {
            Implication leftImplication = (Implication) left;
            Implication rightImplication = (Implication) right;

            currEquations.add(new Equation(leftImplication.getLeft(), rightImplication.getLeft()));
            currEquations.add(new Equation(leftImplication.getRight(), rightImplication.getRight()));
            return true;
        }
        else {
            currEquations.add(equation);
        }
        return false;
    }

    private static boolean toXEqualsT(Term left, Term right, Equation equation, Set<Equation> currEquations) {
        if (!(left instanceof TypeVariable) && right instanceof TypeVariable) {
            currEquations.add(new Equation(right, left));
            return true;
        }
        else {
            currEquations.add(equation);
            return false;
        }
    }

    private static void drainTo(Collection<Equation> prevEquations, Collection<Equation> currEquations) {
        prevEquations.clear();
        prevEquations.addAll(currEquations);
        currEquations.clear();
    }

    /**
     * Производит в уравнения a = b подстановки вида x = T
     * @param equations сет уравнений, в которые нужно сделать подстановки,
     *                      также сет, в котором будут содержаться уравнения после подстановок
     */
    private static boolean substitute(Set<Equation> equations) {
        boolean changed = false;

        ArrayList<Equation> prevEquations = new ArrayList<>(equations);
        ArrayList<Equation> currEquations = new ArrayList<>();

        for (int i = 0; i < prevEquations.size(); i++) {
            Equation substitution = prevEquations.get(i);

            if (!(substitution.getLeft() instanceof TypeVariable)) {
                continue;
            }

            TypeVariable variable = (TypeVariable) substitution.getLeft();

            // Для каждого уравнения вида x = T попробуем подставить во всевозможные уравнения вместо x T,
            // полученный лист будем считать нашим новым набором переменных
            for (Equation equation : prevEquations) {
                // Если они совпадают по ссылке, то это одно и то же выражение, можно скипнуть
                if (substitution == equation) {
                    currEquations.add(substitution);
                    continue;
                }

                if (equation.containsVariable(variable.getName())) {
                    currEquations.add((Equation) equation.substitute(variable.getName(), substitution.getRight()));
                    changed = true;
                }
                else {
                    currEquations.add(equation);
                }
            }

            drainTo(prevEquations, currEquations);
        }

        drainTo(equations, prevEquations);

        return changed;
    }

    private static Term typeSubstitute(Term type, Set<Equation> substitutions) {
        Term currType = type;

        for (Equation substitution : substitutions) {
            TypeVariable variable = (TypeVariable) substitution.getLeft();

            if (type.containsVariable(variable.getName())) {
                currType = currType.substitute(variable.getName(), substitution.getRight());
            }
        }
        return currType;
    }

    private static String getNewName() {
        return Statics.typeNameTemplate + Statics.counter.increment();
    }
}
