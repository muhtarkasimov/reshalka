package yandex.problem3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String operations = "[-+]";
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        line = line.replaceAll(" ", "");


        String expression1 = line.split("=")[0];
        String expression2 = line.split("=")[1];

        char[] operations1 = expression1.replaceAll("\\w*\\d*", "").toCharArray();
        char[] operations2 = expression2.replaceAll("\\w*\\d*", "").toCharArray();

        String[] operands1 = expression1.split(operations);
        String[] operands2 = expression2.split(operations);

        for (int radix = 2; radix < 34; radix++) {
            try {
                int sum1 = Integer.parseInt(operands1[0], radix);
                for (int i = 1; i < operands1.length; i++) {
                    int operand = Integer.parseInt(operands1[i]);
                    sum1 += (operations1[i - 1] == '+') ? operand : -operand;
                }
                int sum2 = Integer.parseInt(operands2[0], radix);
                for (int i = 1; i < operands2.length; i++) {
                    int operand = Integer.parseInt(operands2[i]);
                    sum2 += (operations2[i - 1] == '+') ? operand : -operand;
                }
                if (sum1 == sum2) {
                    System.out.println(radix);
                    System.exit(0);
                }
            } catch (Exception e) {

            }
        }
        System.out.println(-1);
    }

}
