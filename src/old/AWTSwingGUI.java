package Java._2025;

import javax.swing.*;

public class AWTSwingGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello GUI");
        JLabel label = new JLabel("こんにちは GUI!", SwingConstants.CENTER);
        frame.add(label);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
