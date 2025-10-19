
import java.util.Arrays;

// 配列内の整数をライブラリを呼び出して様々な計算処理をする。

class NumberStats {
    public static void main(String[] args) {
        // サンプルデータ
        int[] numbers = {5, 12, 7, 3, 20};

        int sum = Arrays.stream(numbers).sum();

        double average = Arrays.stream(numbers).average().orElse(0);

        int max = Arrays.stream(numbers).max().orElse(Integer.MIN_VALUE);

        int min = Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);

        System.out.println("数値: " + Arrays.toString(numbers));
        System.out.println("合計: " + sum);
        System.out.println("平均: " + average);
        System.out.println("最大値: " + max);
        System.out.println("最小値: " + min);

    }
}