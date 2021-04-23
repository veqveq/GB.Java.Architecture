package FabricMethod;

import java.util.Arrays;

public interface Operation {
    int doOperation(int[] a);
}

class Sum implements Operation {
    @Override
    public int doOperation(int[] a) {
        return Arrays.stream(a).sum();
    }
}

class Sub implements Operation {
    @Override
    public int doOperation(int[] a) {
        for (int i = 1; i < a.length; i++) {
            a[i] = -a[i];
        }
        return Arrays.stream(a).sum();
    }
}
