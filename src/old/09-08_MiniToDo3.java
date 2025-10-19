
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

class MiniToDo3 {
    private static final Path FILE = Path.of("tasks.txt");

    // Taskクラス
    static class Task {
        String title;
        boolean done;

        Task(String title, boolean done) {
            this.title = title;
            this.done = done;
        }

        @Override
        public String toString() {
            return (done ? "[x] " : "[ ] ") + title;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        List<Task> tasks = loadTasks();

        System.out.println("=== MiniToDo アプリ ===");
        System.out.println("コマンド: add <タスク>, list, delete <番号>, done <番号>, exit");

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                saveTasks(tasks);
                System.out.println("終了します。");
                break;
            } else if (input.startsWith("add ")) {
                String task = input.substring(4);
                tasks.add(new Task(task, false));
                System.out.println("タスクを追加しました: " + task);
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("タスク一覧:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ": " + tasks.get(i));
                }
            } else if (input.startsWith("delete ")) {
                try {
                    int idx = Integer.parseInt(input.substring(7)) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        System.out.println("削除しました: " + tasks.remove(idx).title);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("番号が不正です。");
                }
            } else if (input.startsWith("done ")) {
                try {
                    int idx = Integer.parseInt(input.substring(5)) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).done = true;
                        System.out.println("完了にしました: " + tasks.get(idx).title);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("番号が不正です。");
                }
            } else {
                System.out.println("不明なコマンドです。");
            }
        }
    }

    // ファイル保存
    private static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE, StandardCharsets.UTF_8)) {
            for (Task task : tasks) {
                writer.write((task.done ? "1" : "0") + "," + task.title);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ファイル読み込み
    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE)) return tasks;
        try (BufferedReader reader = Files.newBufferedReader(FILE, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                boolean done = parts[0].equals("1");
                String title = parts[1];
                tasks.add(new Task(title, done));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
