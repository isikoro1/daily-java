import java.util.HashMap;
import java.util.Map;

// WordFrequencyCounterの、if文を使ったバージョン
class WordFrequencyCounterIf {
    public static void main(String[] args) {
        String text = "this is a sample this is test sample";

        // 空白で分割
        String[] words = text.split("\\s+");

        // 出現回数を数えるMap
        Map<String, Integer> freqMap = new HashMap<>();

        for (String word : words) {
            if (freqMap.containsKey(word)) {
                // 既に存在するなら値を取り出して +1
                int count = freqMap.get(word);
                freqMap.put(word, count + 1);
            } else {
                // 存在しないなら 1 で初期化
                freqMap.put(word, 1);
            }
  
        }
        System.out.println("元の文章: " + text);
        System.out.println("単語の出現回数: " + freqMap);
    }
}
