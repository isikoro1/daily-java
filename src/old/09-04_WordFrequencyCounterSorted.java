import java.util.Map;
import java.util.TreeMap;

class WordFrequencyCounterSorted {
    public static void main(String[] args) {
        String text = "this is a sample this is test sample";

        // 空白で分割
        String[] words = text.split("\\s+");

        // TreeMapでアルファベット順にキーを並べる
        Map<String, Integer> freqMap = new TreeMap<>();

        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // 出力
        System.out.println("元の文章: " + text);
        System.out.println("アルファベット順の単語出現回数: " + freqMap);
    }
}
