package validators;

public class StringValidator {
    private Validator start;
    private Validator last;


    public StringValidator() {
        register(new WordsCleaner())
                .register(new ParenthesisValidator());
    }

    public StringValidator register(Validator validator) {
        if (start == null) {
            start = validator;
            last = start;
        }
        last.link(validator);
        last = validator;
        return this;
    }

    public String valid(String string){
        StringBuilder sb = new StringBuilder(string);
        return start.check(sb).toString();
    }
}
