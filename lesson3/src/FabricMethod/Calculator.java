package FabricMethod;

import java.util.Arrays;

public abstract class Calculator {
    public void calculate(String msg) {
        Operation operation = create();
        int result = operation.doOperation(parseMsg(msg));
        System.out.println(msg.trim() + "=" + result);
    }

    public abstract Operation create();

    public abstract int[] parseMsg(String msg);
}

class SumCalculator extends Calculator {
    @Override
    public Operation create() {
        return new Sum();
    }

    @Override
    public int[] parseMsg(String msg) {
        return Arrays.stream(msg.split("\\+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}

class SubCalculator extends Calculator {
    @Override
    public Operation create() {
        return new Sub();
    }

    @Override
    public int[] parseMsg(String msg) {
        return Arrays.stream(msg.split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}