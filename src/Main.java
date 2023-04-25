// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String calc(String input) throws IOException {
        Calculator calculator = new Calculator(input);
        calculator.evaluate();
        return calculator.answer;
    }
    public static void main(String[] args) throws IOException {
        String result;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println("You entered string " + input);
        input = input.replaceAll("\\s", "");
        result = calc(input);
        System.out.println(result);
    }

}


