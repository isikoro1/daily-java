import java.io.BufferedReader; // テキストを行単位で効率よく読み込むためのクラス
import java.io.InputStreamReader; // バイトストリームを文字ストリームに変換するためのクラス
import java.net.HttpURLConnection; // HTTP接続を扱うためのクラス
import java.net.URL; // URLを扱うためのクラス

// PokeAPIからポケモンの情報を取得して表示するだけ
class PokeApiTest {
    public static void main(String[] args) {
        try {
            // 1. URLを指定
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/pikachu");

            // 2. 接続を開く
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 3. レスポンスを読み込む
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 4. JSONをそのまま出力
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}