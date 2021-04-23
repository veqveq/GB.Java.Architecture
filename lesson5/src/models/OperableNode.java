package models;

import operations.Operator;

public class OperableNode implements Operable {
    private final Operable leftValue;
    private final Operable rightValue;
    private final Operator operation;

    public OperableNode(Operable leftValue, Operable rightValue, Operator operation) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.operation = operation;
    }

    @Override
    public double getValue() {
        return operation.count(leftValue.getValue(), rightValue.getValue());
    }

    @Override
    public void reverse() {
        leftValue.reverse();
        rightValue.reverse();
    }
}
