

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

// 入力した金額に税率を掛けて表示できる。

class TaxCalculatorInt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("金額を入力してください (円) : ");
        BigDecimal price = sc.nextBigDecimal();

        BigDecimal taxRate = new BigDecimal("0.10"); //消費税10%
        BigDecimal tax = price.multiply(taxRate);

        // 税額を円単位で四捨五入
        tax = tax.setScale(0, RoundingMode.HALF_UP);

        BigDecimal total = price.add(tax);

        System.out.println("税額: " + tax + " 円");
        System.out.println("税込金額: " + total + " 円");

        sc.close();
    }
}
