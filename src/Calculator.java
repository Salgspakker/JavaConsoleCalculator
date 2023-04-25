import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import static java.util.Map.entry;
class Calculator {
    Map<String, String> roman = Map.ofEntries(
            entry("I", "1"),
            entry("II", "2"),
            entry("III", "3"),
            entry("IV", "4"),
            entry("V", "5"),
            entry("VI", "6"),
            entry("VII", "7"),
            entry("IIX", "8"),
            entry("IX", "9"),
            entry("X", "10")
    );
    int a;
    int b;
    String op;
    String answer;
    boolean isRoman = false;

    String toRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        String roman = "";
        for(int i = 0;i < values.length; i++)
        {
            while(num >= values[i])
            {
                num = num - values[i];
                roman = roman.concat(romanLetters[i]);
            }
        }
        return roman;
    }
    String evaluate() {
        System.out.println("a is " + a + " b is " + b + " op is " + op);
        if (op.equals("+")) {
            answer = String.valueOf(a + b);
        }
        if (op.equals("-")) {
            answer = String.valueOf(a - b);
        }
        if (op.equals("*")) {
            answer = String.valueOf(a * b);
        }
        if (op.equals("/")) {
            answer = String.valueOf(a / b);
        }
        if (isRoman) {
            if (Integer.parseInt(answer) > 0) {
                answer = toRoman(Integer.parseInt(answer));
            } else {
                throw new ArithmeticException("Result is equal or less than 0");
            }
        }
        return answer;
    }
    Calculator(String str) throws IOException {
        int signsCount;
        String tmp;
        StringTokenizer st = new StringTokenizer(str, "+-*/");
        signsCount = st.countTokens();
        if (signsCount > 2) {
            throw new IOException("Wrong input from user, no more than 1 sign allowed");
        } else if (signsCount < 2) {
            throw new IOException("Wrong input from user");
        }
        tmp = st.nextToken();
        try {
            a = Integer.parseInt(tmp);
            if ((a > 10) || (a < 1)) {
                throw new ArithmeticException("Wrong input from user, numbers should be in [1,10] range");
            }
        } catch (Exception e) {
           if (roman.get(tmp) == null) {
               throw new IOException("Wrong input from user");
            }
            a = Integer.parseInt(roman.get(tmp));
           isRoman = true;
        }
            try {
                op = Character.toString(str.charAt(str.indexOf(tmp) + tmp.length()));
            } catch (Exception e) {
                throw new IOException("Wrong input from user, incorrect operation");
            }
        tmp = st.nextToken();
        try {
            b = Integer.parseInt(tmp);
            if ((b > 10) || (b < 1)) {
                throw new ArithmeticException("Wrong input from user, numbers should be in [1,10] range");
            }
            if (isRoman) {
                throw new IOException("Wrong input from user");
            }
        } catch (Exception e) {
            if (roman.get(tmp) == null) {
                throw new IOException("Wrong input from user");
            }
            if(isRoman) {
                b = Integer.parseInt(roman.get(tmp));
            } else {
                throw new IOException("Wrong input from user");
            }
        }
    }
}
