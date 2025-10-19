import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class CSVReader2 {
    public static void main(String[] args) {
        Path file = Path.of("input.csv");

        try {
            List<String> lines = Files.readAllLines(file);

            System.out.println("=== CSV内容 ===");
            int totalAge = 0;
            int count = 0;


            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] columns = line.split(",");

                // 表形式で整形（幅指定）
                for (String col : columns) {
                    System.out.printf("%-10s", col);
                    // %s = 文字列を出力
                    // 10 = 幅を10文字に固定
                    // - = 左寄せ
                }
                System.out.println();

                // 1行目（ヘッダー）はスキップ
                if (i > 0) {
                    try {
                        int age = Integer.parseInt(columns[1]); // Age列
                        totalAge += age;
                        count++;
                    } catch (NumberFormatException e) {
                        System.out.println("年齢の値が不正: " + columns[1]);
                    }
                }    
            }

            if (count > 0) {
                double avg = (double) totalAge / count;
                System.out.printf("平均年齢: %.2f%n", avg);
            }

        } catch (IOException e) {
            System.out.println("エラー: " + e.getMessage());
        }
    }
}