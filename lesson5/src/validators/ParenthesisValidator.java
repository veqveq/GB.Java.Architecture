package validators;

import java.util.Stack;

public class ParenthesisValidator extends Validator {
    @Override
    void checking(StringBuilder sb) {
        Stack<Integer> parenthesisStack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i)=='('){
                parenthesisStack.push(i);
            }
            if (sb.charAt(i)==')'){
                if (!parenthesisStack.isEmpty()){
                    parenthesisStack.pop();
                }else{
                    sb.deleteCharAt(i);
                }
            }
        }
        if (!parenthesisStack.isEmpty()){
            for (int i = 0; i < parenthesisStack.size(); i++) {
                sb.deleteCharAt(parenthesisStack.pop());
            }
        }
    }
}
