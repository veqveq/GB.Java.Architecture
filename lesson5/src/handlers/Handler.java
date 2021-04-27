package handlers;

import models.Operable;

import java.util.List;

public abstract class Handler {
    Handler next;

    public Handler link(Handler handler) {
        this.next = handler;
        return next;
    }

    public List<Operable> check(List<Operable> list) {
        checking(list);
        return checkNext(list);
    }

    abstract void checking(List<Operable> list);

    private List<Operable> checkNext(List<Operable> list) {
        if (hasNext()) return next.check(list);
        return list;
    }

    private boolean hasNext() {
        return next != null;
    }
}
