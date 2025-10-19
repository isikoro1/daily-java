
import java.util.HashMap;
import java.util.Map;

// 単語の出現回数を表示する

class WordFrequencyCounter {
    public static void main(String[] args) {
        String text = "this is a sample this is test sample";

        // 空白で分割
        String[] words = text.split("\\s+");

        // 出現回数を数えるMap
        Map<String, Integer> freqMap = new HashMap<>();
        //MapはPythonのdict、C#のDictionaryと同じ
        
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // 出力
        System.out.println("元の文章: " + text);
        System.out.println("単語の出現回数: " + freqMap);

    }
}
