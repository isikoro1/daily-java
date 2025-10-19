package Java._2025;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

class MiniToDo9 {
    private static final Path FILE = Path.of("tasks.txt");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    static class Task {
        String title;
        boolean done;
        LocalDate dueDate; // 追加: 期限日

        Task(String title, boolean done, LocalDate dueDate) {
            this.title = title;
            this.done = done;
            this.dueDate = dueDate;
        }

        @Override
        public String toString() {
            String base = (done ? "[x] " : "[ ] ") + title;
            if (dueDate != null) {
                base += "（期限: " + dueDate.format(DATE_FORMAT) + "）";
            }
            return base;
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
        System.out.println("  add <title> [期限: YYYY-MM-DD]    : タスクを追加（期限は任意）");
        System.out.println("  list                              : タスクを全件表示");
        System.out.println("  list [done|undone|overdue]        : タスクを一覧表示");
        System.out.println("  delete <番号>                     : タスクを削除");
        System.out.println("  done <番号>                       : タスクを完了");
        System.out.println("  edit <番号> <新タスク>             : タスクを編集（期限はそのまま）");
        System.out.println("  search <キーワード>               : キーワードでタスクを検索");
        System.out.println("  help                              : このヘルプを表示");
        System.out.println("  exit                              : 終了");
    }

    private static void addTask(List<Task> tasks, String input) {
        if (input.isEmpty()) {
            System.out.println("タスクのタイトルを指定してください。");
            return;
        }

        String[] parts = input.split(" ", 2);
        String title = parts[0];
        LocalDate dueDate = null;

        if (parts.length > 1) {
            try {
                dueDate = LocalDate.parse(parts[1].trim(), DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("期限の日付形式が不正です。YYYY-MM-DD形式で指定してください。");
                return;
            }
        }

        tasks.add(new Task(title, false, dueDate));
        System.out.println("タスクを追加しました。" + title +
                (dueDate != null ? "（期限: " + dueDate + "）" : ""));
    }

    private static void listTasks(List<Task> tasks, String mode) {
        System.out.println("=== タスクリスト ===");
        LocalDate today = LocalDate.now();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            boolean isDone = task.done;
            boolean isUndone = !task.done;
            boolean isOverdue = task.dueDate != null && task.dueDate.isBefore(today) && !task.done;

            boolean shouldDisplay = mode.equals("all") ||
                    (mode.equals("done") && isDone) ||
                    (mode.equals("undone") && isUndone) ||
                    (mode.equals("overdue") && isOverdue);

            if (shouldDisplay) {
                System.out.println("  " + (i + 1) + ": " + task);
            }
        }
    }

    private static void deleteTask(List<Task> tasks, String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.println("タスクを削除しました。");
            } else {
                System.out.println("無効なタスク番号です。");
            }
        } catch (NumberFormatException e) {
            System.out.println("削除するタスクの番号を指定してください。");
        }
    }

    private static void doneTask(List<Task> tasks, String arg) {
        try {
            int index = Integer.parseInt(arg) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).done = true;
                System.out.println("タスクを完了しました: " + tasks.get(index).title);
            } else {
                System.out.println("無効なタスク番号です。");
            }
        } catch (NumberFormatException e) {
            System.out.println("完了するタスクの番号を指定してください。");
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
            System.out.println("タスクを更新しました: " + oldTitle + " -> " + parts[2]);
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
                System.out.println("  " + (i + 1) + ": " + task);
            }
        }
    }

    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE))
            return tasks;
        try (BufferedReader reader = Files.newBufferedReader(FILE, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                boolean done = parts[0].equals("1");
                String title = parts.length > 1 ? parts[1] : "";
                LocalDate dueDate = null;
                if (parts.length > 2 && !parts[2].isBlank()) {
                    try {
                        dueDate = LocalDate.parse(parts[2], DATE_FORMAT);
                    } catch (DateTimeParseException e) {
                        System.out.println("期限の読み込みに失敗: " + parts[2]);
                    }
                }
                tasks.add(new Task(title, done, dueDate));
            }
        } catch (IOException e) {
            System.out.println("タスクの読み込みに失敗しました: ");
        }
        return tasks;
    }

    private static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE, StandardCharsets.UTF_8)) {
            for (Task task : tasks) {
                String due = (task.dueDate != null) ? task.dueDate.format(DATE_FORMAT) : "";
                writer.write((task.done ? "1" : "0") + "," + task.title + "," + due);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("タスクの保存に失敗しました。");
        }

    }

}