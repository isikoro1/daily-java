
import java.util.Scanner;

//摂氏と華氏の温度を変換できます。

class TemperatureConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("摂氏の温度を入力してください (例: 25):");
        double celsius = sc.nextDouble();

        double fahrenheit = celsius * 9 / 5 + 32;
        System.out.println("華氏: " + fahrenheit);

        System.out.print("華氏の温度を入力してください (例: 77):");
        double f = sc.nextDouble();

        double c = (f - 32) * 5 / 9;
        System.out.println("摂氏: " + c);

        sc.close();
    }
}