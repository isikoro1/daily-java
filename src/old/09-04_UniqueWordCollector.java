
import java.util.*;

class UniqueWordCollector {
    public static void main(String[] args) {
        String text = "this is a sample this is test sample";


        // 空白で分割
        String[] words = text.split("\\s+");

        // 重複しない単語を格納
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        // 出力
        System.out.println("元の文章: " + text);
        System.out.println("ユニーク単語: " + uniqueWords);
    }
}
