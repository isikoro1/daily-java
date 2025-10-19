package Java._2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class CSVVertical {
    public static void main(String[] args) {
        Path file = Path.of("input.csv");

        try {
            List<String> lines = Files.readAllLines(file);

            // 1行目をヘッダーとして使う
            String[] headers = lines.get(0).split(",");

            // それぞれの列を順番に出力
            for (int col = 0; col < headers.length; col++) {
                System.out.printf("%-10s: ", headers[col]);

                // 2行目以降のデータを縦に並べる
                for (int row = 1; row < lines.size(); row++) { // size()は要素数を返す
                    String[] columns = lines.get(row).split(",");
                    if (col < columns.length) {
                        System.out.print(columns[col] + " ");
                    }
                }
                System.out.println(); // 改行
            }

        } catch (IOException e) {
            System.out.println("エラー: " + e.getMessage());
        }
    }
}
// 配列や文字列はlength, コレクションはsize()。なぜ違うメソッドを使うのかは大人の都合上らしい。