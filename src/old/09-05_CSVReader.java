
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class CSVReader {
    public static void main(String[] args) {
        Path file = Path.of("input.csv");

        try {
            List<String> lines = Files.readAllLines(file);

            System.out.println("=== CSV内容 ===");
            for (String line : lines) {
                String[] columns = line.split(",");
                for (String col : columns) {
                    System.out.print(col + "\t"); // タブ区切りで整形
                }
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("エラー: " + e.getMessage());
        }
    }
    
}