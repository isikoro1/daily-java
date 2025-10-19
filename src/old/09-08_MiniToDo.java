import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class MiniToDo2 {
    private static final Path FILE = Path.of("tasks.txt");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> tasks = loadTasks();

        System.out.println("=== MiniToDo アプリ ===");

        while (true) {
            System.out.println("コマンド: add <タスク>, list, delete <番号>, exit");
            System.out.print("> ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                saveTasks(tasks);
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
                    System.out.println("タスクを追加しました: " + task);
                } else {
                    System.out.println("タスクを入力してください。");
                }
            } else if (input.startsWith("delete ")) {
                try {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        String removed = tasks.remove(index);
                        System.out.println("タスクを削除しました: " + removed);
                    } else {
                        System.out.println("無効な番号です。");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("番号を正しく入力してください。");
                }
            } else {
                System.out.println("不明なコマンドです。");
            }
        }
    
    
        sc.close();
    }



    // タスクをファイルから読み込む
    private static List<String> loadTasks() {
        try {
            if (Files.exists(FILE)) {
                return new ArrayList<>(Files.readAllLines(FILE, java.nio.charset.StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            System.out.println("タスクの読み込みに失敗しました: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // タスクをファイルに保存
    private static void saveTasks(List<String> tasks) {
        try {
            Files.write(FILE, tasks, java.nio.charset.StandardCharsets.UTF_8);
            System.out.println("タスクを保存しました。");
        } catch (IOException e) {
            System.out.println("タスクの保存に失敗しました: " + e.getMessage());
        }
    }

}