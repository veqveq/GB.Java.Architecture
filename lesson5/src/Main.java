import models.OperableList;

public class Main {
    public static void main(String[] args) {
        String operation = "-(3+2*(1+1))";
        System.out.println(calculate(operation));
    }

    private static double calculate(String string){
        return new OperableList(string).getValue();
    }
}
