package handlers;

import models.Operable;
import operations.Operator;

import java.util.List;

public class FinishedHandler extends Handler {
    @Override
    void checking(List<Operable> list) {
        if (list.get(list.size()-1) instanceof Operator) {
            list.remove(list.size()-1);
            checking(list);
        }
    }
}
