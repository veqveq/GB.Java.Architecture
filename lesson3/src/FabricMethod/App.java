package FabricMethod;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = null;
        String msg;
        do {
            msg = sc.nextLine();
            if (msg.contains("+")) {
                calculator = new SumCalculator();
            }else if (msg.contains("-")){
                calculator = new SubCalculator();
            }
            calculator.calculate(msg);
        } while (!msg.contains("end"));
    }
}
