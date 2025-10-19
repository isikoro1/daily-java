package Java._2025;

import javax.swing.*;

public class AWTSwingGUI2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello GUI");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ラベル作成（中央に表示）
        JLabel label = new JLabel("こんにちは GUI!", SwingConstants.CENTER);

        // ボタン作成
        JButton button = new JButton("クリックしてください");

        // ボタンを押したときの処理を追加(ラムダ式)
        button.addActionListener(e -> label.setText("ボタンが押されました"));

        // ウィンドウに部品を配置 (BorderLayoutを利用)
        frame.add(label, java.awt.BorderLayout.CENTER); // 中央にラベル
        frame.add(button, java.awt.BorderLayout.SOUTH); // 下部にボタン

        frame.setVisible(true);

    }
}
