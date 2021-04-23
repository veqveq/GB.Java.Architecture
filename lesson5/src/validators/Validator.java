package validators;

public abstract class Validator {
    private Validator next;

    public Validator link(Validator validator) {
        this.next = validator;
        return next;
    }


    public StringBuilder check(StringBuilder sb) {
        checking(sb);
        return checkNext(sb);
    }

    abstract void checking(StringBuilder sb);

    private StringBuilder checkNext(StringBuilder sb) {
        if (hasNext()) return next.check(sb);
        return sb;
    }

    private boolean hasNext() {
        return next != null;
    }
}
