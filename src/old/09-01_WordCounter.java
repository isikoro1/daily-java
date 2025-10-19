

class WordCounter {
    public static void main(String[] args) {
        String text = "This is a sample sentence.";

        // 文字数（空白含む）
        int charCount = text.length();

        // 単語数 （空白で分割）
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        System.out.println("文字数: " + charCount);
        System.out.println("単語数: " + wordCount);
    }   
}

// split("...") : 引数に 正規表現（Regex） を渡して、文字列を分割するメソッド。

// \\s          :  正規表現で「空白文字」を意味する。空白スペース " "、タブ \t、改行 \n などを含む。

//  +           :「1回以上繰り返す」という意味。

//  つまり \\s+ =   : 「空白文字が1個以上続く場所」