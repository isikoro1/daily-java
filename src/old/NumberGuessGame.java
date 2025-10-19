package Java._2025;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int answer = rand.nextInt(100) + 1;
        int guess = 0;
        int attempts = 0;

        System.out.println("=== 数当てゲーム ===");
        System.out.println("1~100の数字を当ててください!");

        while (guess != answer) {
            System.out.print("入力 > ");
            guess = sc.nextInt();
            attempts++;

            if (guess < answer) {
                System.out.println("もっと大きいです");
            } else if (guess > answer) {
                System.out.println("もっと小さいです");
            } else {
                System.out.println("正解! " + attempts + "回で当たりました!");
            }
        }

        sc.close();
    }
}