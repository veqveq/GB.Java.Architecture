package operations;

import models.Operable;

import java.util.function.Function;

public enum Operator implements Operable {
    SUM(Double::sum, 3, new Function<Operator, Operator>() {
        @Override
        public Operator apply(Operator operator) {
            if (operator == Operator.SUB) {
                return SUB;
            }
            return null;
        }
    }),
    SUB((a1, a2) -> a1 - a2, 3, new Function<Operator, Operator>() {
        @Override
        public Operator apply(Operator operator) {
            switch (operator) {
                case SUB:
                    return SUM;
                default:
                    return SUB;
            }
        }
    }),
    MULTI((a1, a2) -> a1 * a2, 2, new Function<Operator, Operator>() {
        @Override
        public Operator apply(Operator operator) {
            if (operator == Operator.SUB) {
                return SUB;
            }
            return null;
        }
    }),
    DIV((a1, a2) -> a1 / a2, 2, new Function<Operator, Operator>() {
        @Override
        public Operator apply(Operator operator) {
            if (operator == Operator.SUB) {
                return SUB;
            }
            return null;
        }
    }),
    POW(Math::pow, 1, new Function<Operator, Operator>() {
        @Override
        public Operator apply(Operator operator) {
            if (operator == Operator.SUB) {
                return SUB;
            }
            return null;
        }
    });

    private final Operation operation;
    private final int priority;
    private final Function<Operator, Operator> convert;

    Operator(Operation operation, int priority, Function<Operator, Operator> function) {
        this.operation = operation;
        this.priority = priority;
        this.convert = function;
    }

    public double count(double a, double b) {
        return operation.doOperation(a, b);
    }

    @Override
    public double getValue() {
        return priority;
    }

    @Override
    public void reverse() {

    }

    public Operator reform(Operator operator) {
        return convert.apply(operator);
    }
}