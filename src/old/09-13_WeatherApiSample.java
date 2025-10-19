package September;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class WeatherApiSample {
    public static void main(String[] args) {
        // APIキー（自分で登録して取得した物を入れる。平文保存はNG。環境変数や.envで.gitignore、暗号化などで保存）
        String apiKey = "YOUR_API_KEY";
        String city = "Tokyo"; // 東京の天気
        String urlStr = "http://api.openweathermap.org/data/2.5/weather?q="
                        + city + "&appid=" + apiKey + "&units=metric&lang=ja";

        try {
            // URLオブジェクトを作成
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // レスポンスを読み取り
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
            );
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // JSONの生データを出力
            System.out.println("=== API Response ===");
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
} 