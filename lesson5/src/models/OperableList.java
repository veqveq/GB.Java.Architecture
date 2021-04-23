package models;

import handlers.Formatter;
import operations.Operator;
import validators.StringValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperableList implements Operable {
    private final List<Operable> data;
    private int size;

    public OperableList(String string) {
        Formatter formatter = new Formatter();
        StringValidator stringValidator = new StringValidator();
        String validString = stringValidator.valid(string);
        List<Operable> parsedList = parse(validString);
        this.data = formatter.format(parse(stringValidator.valid(string)));
        sort();
    }

    private List<Operable> parse(String string) {

        StringBuilder currentWord = new StringBuilder();
        List<Operable> list = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                size++;
                OperableList currentList = new OperableList(
                        new StringBuilder(string).delete(0, i + 1).toString());
                list.add(currentList);
                size += currentList.getSize();
                i += currentList.getSize();
                continue;
            }
            if (string.charAt(i) == ')') {
                size++;
                break;
            }

            Optional<Operator> currentOperation = checkChar(string.charAt(i));

            if (currentOperation.isPresent()) {
                if (currentWord.length() != 0) {
                    list.add(new OperableValue(Double.parseDouble(currentWord.toString())));
                }
                currentWord.setLength(0);
                list.add(currentOperation.get());
            } else {
                currentWord.append(string.charAt(i));
            }
            size++;
        }
        if (currentWord.length() != 0) {
            list.add(new OperableValue(Double.parseDouble(currentWord.toString())));
        }
        return list;
    }

    private void sort() {
        for (int priority = 0; priority < 5; priority++) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) instanceof Operator
                        && (data.get(i)).getValue() == priority) {
                    data.set(i,
                            new OperableNode(
                                    data.get(i - 1),
                                    data.get(i + 1),
                                    (Operator) data.get(i)
                            )
                    );
                    data.remove(i - 1);
                    data.remove(i);
                    sort();
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    private Optional<Operator> checkChar(char c) {
        switch (c) {
            case '+':
                return Optional.of(Operator.SUM);
            case '-':
                return Optional.of(Operator.SUB);
            case '*':
                return Optional.of(Operator.MULTI);
            case '/':
                return Optional.of(Operator.DIV);
            case '^':
                return Optional.of(Operator.POW);
            default:
                return Optional.empty();
        }
    }

    @Override
    public double getValue() {
        return (data.get(0)).getValue();
    }

    @Override
    public void reverse() {
        data.forEach(Operable::reverse);
    }

    private class Pipeline{

    }
}
