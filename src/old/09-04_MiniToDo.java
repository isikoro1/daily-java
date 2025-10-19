

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ChatGPT出力を写経 2025.9.4
// 分からない部分の学習はnotes/2025/09-05_TIL_MiniToDo.mdに記述

class MiniToDo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        System.out.println("=== MiniToDo アプリ ===");
        System.out.println("コマンド: add <タスク>, list, delete <番号>, exit");

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("終了します。");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("タスクはありません。");
                } else {
                    System.out.println("タスク一覧:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ": " + tasks.get(i));
                    }
                }
            } else if (input.startsWith("add ")) {
                String task = input.substring(4).trim();
                if (!task.isEmpty()) {
                    tasks.add(task);
                    System.out.println("追加しました: " + task);
                } else {
                    System.out.println("タスク内容を入力してください。");
                }
            } else if (input.startsWith("delete ")) {
                try {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        String removed = tasks.remove(index);
                        System.out.println("削除しました: " + removed);
                    } else {
                        System.out.println("番号が無効です。");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("数字を指定してください。");
                }
            } else {
                System.out.println("不明なコマンドです。");
            }
        }

        sc.close();
    }
}
