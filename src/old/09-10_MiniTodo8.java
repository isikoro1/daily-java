import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

class MiniToDo8 {
    private static final Path FILE = Path.of("tasks.txt");

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

        System.out.println("=== Mini ToDo List ===");
        printHelp();

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            if (input.isEmpty())
                continue;

            String[] parts = input.split(" ", 2);
            String cmd = parts[0];
            String arg = parts.length > 1 ? parts[1] : "";

            switch (cmd) {
                case "exit" -> {
                    saveTasks(tasks);
                    System.out.println("終了します。");
                    return;
                }
                case "help" -> printHelp();
                case "add" -> addTask(tasks, arg);
                case "list" -> listTasks(tasks, arg.isEmpty() ? "all" : arg);
                case "delete" -> deleteTask(tasks, arg);
                case "done" -> doneTask(tasks, arg);
                case "edit" -> editTask(tasks, input);
                case "search" -> searchTasks(tasks, arg);
                default -> System.out.println("不明なコマンドです。helpコマンドで確認してください。");
            }
        }
    }

    // ==== コマンド一覧 ====
    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  add <タスク>         : タスクを追加");
        System.out.println("  list                 : タスクを一覧表示");
        System.out.println("  list done            : 完了したタスクを表示");
        System.out.println("  list undone          : 未完了のタスクを表示");
        System.out.println("  delete <番号>        : タスクを削除");
        System.out.println("  done <番号>          : タスクを完了");
        System.out.println("  edit <番号> <新タスク> : タスクを編集");
        System.out.println("  search <キーワード>  : キーワードでタスクを検索");
        System.out.println("  help                 : このヘルプを表示");
        System.out.println("  exit                 : 終了");
    }

    private static void addTask(List<Task> tasks, String title) {
        if (title.isBlank()) {
            System.out.println("タスクの内容を指定してください。");
            return;
        }
        tasks.add(new Task(title, false));
        System.out.println("タスクを追加しました: " + title);
    }

    private static void listTasks(List<Task> tasks, String mode) {
        System.out.println("=== タスクリスト ===");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (mode.equals("all") ||
                    (mode.equals("done") && task.done) ||
                    (mode.equals("undone") && !task.done)) {
                System.out.println("  " + (i + 1) + ": " + task);
            }
        }
    }

    private static void deleteTask(List<Task> tasks, String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            if (index < 0 || index >= tasks.size()) {
                System.out.println("無効なタスク番号です。");
                return;
            }
            tasks.remove(index);
            System.out.println("タスクを削除しました。");
        } catch (NumberFormatException e) {
            System.out.println("タスク番号を指定してください。");
        }
    }

    private static void doneTask(List<Task> tasks, String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            if (index < 0 || index >= tasks.size()) {
                System.out.println("無効なタスク番号です。");
                return;
            }
            tasks.get(index).done = true;
            System.out.println("タスクを完了しました。");
        } catch (NumberFormatException e) {
            System.out.println("タスク番号を指定してください。");
        }
    }

    private static void editTask(List<Task> tasks, String input) {
        try {
            String[] parts = input.split(" ", 3);
            if (parts.length < 3) {
                System.out.println("編集するタスク番号と新しい内容を指定してください。");
                return;
            }

            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                System.out.println("無効なタスク番号です。");
                return;
            }
            String oldTitle = tasks.get(index).title;
            tasks.get(index).title = parts[2];
            System.out.println("タスクを編集しました。: " + oldTitle + " -> " + parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("editコマンドは 'edit <番号> <新タイトル>' の形式です。");
        }
    }

    private static void searchTasks(List<Task> tasks, String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        System.out.println("=== 検索結果 ===");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.title.toLowerCase().contains(lowerKeyword)) {
                System.out.println((i + 1) + ": " + task);
            }
        }
    }

    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        if (Files.exists(FILE)) {
            try (BufferedReader reader = Files.newBufferedReader(FILE, StandardCharsets.UTF_8)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", 2);
                    boolean done = parts[0].equals("1");
                    String title = parts.length > 1 ? parts[1] : "";
                    tasks.add(new Task(title, done));
                }
            } catch (IOException e) {
                System.out.println("タスクの読み込みに失敗しました。");
            }
        }
        return tasks;
    }

    private static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE, StandardCharsets.UTF_8)) {
            for (Task task : tasks) {
                writer.write((task.done ? "1" : "0") + "," + task.title);

                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("タスクの保存に失敗しました。");
        }
    }
}