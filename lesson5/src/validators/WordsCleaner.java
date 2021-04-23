package validators;

public class WordsCleaner extends Validator {
    @Override
    void checking(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) < 40 || (sb.charAt(i) > 57 && sb.charAt(i) != 94)) {
                sb.deleteCharAt(i);
                i--;
            }
        }
    }
}
