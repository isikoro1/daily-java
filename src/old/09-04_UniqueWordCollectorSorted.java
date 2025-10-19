
import java.util.*;

class UniqueWordCollectorSorted {
    public static void main(String[] args) {
        String text = "this is a sample this is test sample desu";

        // 空白で分割
        String[] words = text.split("\\s+");

        // TreeSetでアルファベット順にソート
        Set<String> uniqueWords = new TreeSet<>(Arrays.asList(words));

        // 出力
        System.out.println("元の文章: " + text);
        System.out.println("アルファベット順のユニーク単語: " + uniqueWords);
    }
}
