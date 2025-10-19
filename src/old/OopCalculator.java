
import java.util.Scanner;

// 抽象クラス:全ての演算の共通の型
abstract class Operation {
    double a, b;

    Operation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    abstract double calculate(); // サブクラスで実装
}

// 足し算クラス
class Addition extends Operation {
    Addition(double a, double b) {
        super(a, b);
    }

    @Override
    double calculate() {
        return a + b;
    }
}

// 引き算クラス
class Subtraction extends Operation {
    Subtraction(double a, double b) {
        super(a, b);
    }

    @Override
    double calculate() {
        return a - b;
    }
}

// 掛け算クラス
class Multiplication extends Operation {
    Multiplication(double a, double b) {
        super(a, b);
    }

    @Override
    double calculate() {
        return a * b;
    }
}

// 割り算クラス
class Division extends Operation {
    Division(double a, double b) {
        super(a, b);
    }

    @Override
    double calculate() {
        if (b == 0) {
            throw new ArithmeticException("0では割れません。");
        }
        return a / b;
    }
}

public class OopCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== オブジェクト指向電卓 ===");
            System.out.println("1: 足し算");
            System.out.println("2: 引き算");
            System.out.println("3: 掛け算");
            System.out.println("4: 割り算");
            System.out.println("0: 終了");
            System.out.println("選んでください");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("終了します");
                break;
            }

            System.out.println("１つ目の数値: ");
            double a = sc.nextDouble();
            System.out.println("２つ目の数値: ");
            double b = sc.nextDouble();

            Operation op;
            switch (choice) {
                case 1:
                    op = new Addition(a, b);
                    break;
                case 2:
                    op = new Subtraction(a, b);
                    break;
                case 3:
                    op = new Multiplication(a, b);
                    break;
                case 4:
                    op = new Division(a, b);
                    break;
                default:
                    System.out.println("無効な選択です。");
                    continue;
            }

            try {
                System.out.println("結果: " + op.calculate());
            } catch (ArithmeticException e) {
                System.out.println("エラー: " + e.getMessage());
            }

            System.out.println();
        }
    }

}
