import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;


class WordSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("検索する単語を入力してください: ");
        String keyword = sc.nextLine().trim();

        Path file = Path.of("input.txt"); // 検索対象のファイル

        try {
            List<String> lines = Files.readAllLines(file);
            
            System.out.println("=== 検索結果 ===");
            boolean found = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.contains(keyword)) {
                    System.out.println((i + 1) + ": " + line);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("該当する行はありません。");
            }
        } catch (IOException e) {
            System.out.println("エラー: " + e.getMessage());
        }

        sc.close();
    }
}
