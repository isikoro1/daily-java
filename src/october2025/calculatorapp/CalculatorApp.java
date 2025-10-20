package october2025.calculatorapp;

import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.println("=== Java 電卓 ===");
        while (true) {
            System.out.print("数値1 (qで終了): ");
            String input = sc.next();
            if (input.equalsIgnoreCase("q"))
                break;

            double a = Double.parseDouble(input);

            System.out.print("演算子 (+ - * /): ");
            String opStr = sc.next();
            Operation op = switch (opStr) {
                case "+" -> Operation.ADD;
                case "-" -> Operation.SUB;
                case "*" -> Operation.MUL;
                case "/" -> Operation.DIV;
                default -> throw new IllegalArgumentException("無効な演算子です");
            };

            System.out.print("数値2: ");
            double b = sc.nextDouble();

            double result = calc.calculate(a, b, op);
            System.out.println("結果: " + result);
        }
        System.out.println("終了します。");
        sc.close();
    }
}
