package Java._2025;

import java.util.Scanner;

public class UnitConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== 単位変換ツール ===");
            System.out.println("1: cm → inch");
            System.out.println("2: inch → cm");
            System.out.println("3: kg → lb");
            System.out.println("4: lb → kg");
            System.out.println("0: 終了");
            System.out.println("番号を選んでください: ");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("終了します。");
                break;
            }

            System.out.print("数値を入力してください: ");
            double value = sc.nextDouble();

            switch (choice) {
                case 1:
                    System.out.printf("%.2f cm = %.2f inch%n", value, value / 2.54);
                    break;
                case 2:
                    System.out.printf("%.2f inch = %.2f cm%n", value, value * 2.54);
                    break;
                case 3:
                    System.out.printf("%.2f kg = %.2f lb%n", value, value * 2.20462);
                    break;
                case 4:
                    System.out.printf("%.2f lb = %.2f kg%n", value, value / 2.20462);
                    break;
                default:
                    System.out.println("無効な選択です");
            }
        }
        System.out.println();
    }
}