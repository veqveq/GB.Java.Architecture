package handlers;

import models.Operable;
import operations.Operator;

import java.util.List;

public class IntervalHandler extends Handler {
    @Override
    void checking(List<Operable> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Operator
                    && list.get(i + 1) instanceof Operator) {

                Operator operator1 = (Operator) list.get(i);
                Operator operator2 = (Operator) list.get(i + 1);

                operator2 = operator1.reform(operator2);

                if (operator2 != null) {
                    list.set(i, operator2);
                }
                list.remove(i+1);
                checking(list);
            }
        }
    }
}
