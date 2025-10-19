
import java.util.Scanner;

// 簡易電卓プログラム

public class DailyCode_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== 簡易電卓 ===");
            System.out.println("1: 足し算");
            System.out.println("2: 引き算");
            System.out.println("3: 掛け算");
            System.out.println("4: 割り算");
            System.out.println("0: 終了");
            System.out.println("選んでください: ");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("終了します。");
                break;
            }

            System.out.print("1つ目の数値: ");
            double a = sc.nextDouble();
            System.out.print("2つ目の数値: ");
            double b = sc.nextDouble();

            switch (choice) {
                case 1:
                    System.out.println("結果: " + (a + b));
                    break;
                case 2:
                    System.out.println("結果: " + (a - b));
                    break;
                case 3:
                    System.out.println("結果: " + (a * b));
                    break;
                case 4:
                    if (b == 0) {
                        System.out.println("0では割れません");
                    } else {
                        System.out.println("結果: " + (a / b));
                    }
                    break;
                default:
                    System.out.println("無効な選択です。");
            }

            System.out.println();
        }
        sc.close();
    }
}
