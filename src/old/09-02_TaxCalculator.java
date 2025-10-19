
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class TaxCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("金額を入力してください: ");
        BigDecimal price = sc.nextBigDecimal();

        BigDecimal taxRate = new BigDecimal("0.10"); // 逆に文字列で渡した方が安全
        BigDecimal tax = price.multiply(taxRate);
        BigDecimal total = price.add(tax);

        // 小数点2桁で四捨五入
        total = total.setScale(2, RoundingMode.HALF_UP);

        System.out.println("税額: " + tax);
        System.out.println("税込金額: " + total);

        sc.close();
    }
}
