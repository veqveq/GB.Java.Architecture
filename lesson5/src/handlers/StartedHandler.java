package handlers;

import models.Operable;
import operations.Operator;

import java.util.List;

public class StartedHandler extends Handler {
    @Override
    void checking(List<Operable> list) {
        if (list.get(0) instanceof Operator) {
            Operator operator1 = (Operator) list.get(0);
            if (list.get(1) instanceof Operator) {
                Operator operator2 = (Operator) list.get(1);
                operator2 = operator1.reform(operator2);
                if (operator2 == null || operator2 == Operator.SUM) {
                    list.remove(1);
                }else{
                    list.set(1, operator2);
                }
                list.remove(0);
                checking(list);
            }else{
                list.get(1).reverse();
                list.remove(0);
            }
        }
    }
}
