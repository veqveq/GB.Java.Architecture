package handlers;

import models.Operable;

import java.util.List;

public class Formatter {
    private Handler start;
    private Handler last;

    public Formatter() {
        register(new StartedHandler())
                .register(new FinishedHandler())
                .register(new IntervalHandler());
    }

    public Formatter register(Handler handler) {
        if (start == null) {
            start = handler;
            last = start;
        }
        last.link(handler);
        last = handler;
        return this;
    }

    public List<Operable> format(List<Operable>list){
        return start.check(list);
    }
}
