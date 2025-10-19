package Java._2025;

import java.nio.file.*;
import java.io.IOException;

class LineCounter {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("使い方: java LineCounter <ファイル名>");
            return;
        }

        try {
            Path path = Paths.get(args[0]); // 引数からファイル名を取得
            long lines = Files.lines(path).count();
            System.out.println("行数: " + lines);

        } catch (IOException e) {
            System.out.println("ファイルを読み込めませんでした: " + e.getMessage());
        }

    }
}
