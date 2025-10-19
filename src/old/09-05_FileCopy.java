import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

class FileCopy {
    public static void main(String[] args) {
        Path source = Path.of("input.txt"); // 「実行した時のカレントディレクトリ」が基準
        // ディレクトリの変更方法
        // 例:
        // Path source = Path.of("codes", "Scripts", "input.txt");
        Path target = Path.of("output.txt");

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("コピーが完了しました: " + target);
        } catch (IOException e) {
            System.out.println("エラー: " + e.getMessage());
        }
    }    
}
