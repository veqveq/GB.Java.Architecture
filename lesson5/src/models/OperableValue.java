package models;

public class OperableValue implements Operable {
    private double value;

    public OperableValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void reverse() {
        value = -value;
    }
}
