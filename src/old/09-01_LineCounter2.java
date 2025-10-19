package Java._2025;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;

class LineCounter2 {
    public static void main(String[] args) {
        // ファイル選択ダイアログを開く
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                long lines = Files.lines(file.toPath()).count();
                JOptionPane.showMessageDialog(null,
                        file.getName() + " の行数: " + lines,
                        "結果",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "ファイルを読み込めませんでした: " + e.getMessage(),
                        "エラー",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("キャンセルされました。");
        }
    }
}
