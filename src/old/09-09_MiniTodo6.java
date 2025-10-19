import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

class MiniToDo6 {
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

        while (true) {
            System.out.println("Commands: add <タスク>, list, list done, list undone, delete <番号>, done <番号>, edit <番号> <新しいタスク>, search <キーワード>, exit");

            System.out.print("> ");
            String input = sc.nextLine().trim();

            if (input.equals("exit")) {
                saveTasks(tasks);
                System.out.println("終了します。");
                break;

            } else if (input.startsWith("add ")) {
                String task = input.substring(4);
                tasks.add(new Task(task, false));
                System.out.println("タスクを追加しました: " + task);

            } else if (input.equals("list")) {
                listTasks(tasks, "all");

            } else if (input.equals("list done")) {
                listTasks(tasks, "done");

            } else if (input.equals("list undone")) {
                listTasks(tasks, "undone");

            } else if (input.startsWith("delete ")) {
                try {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        System.out.println("タスクを削除しました: " + tasks.remove(index).title);
                    } else {
                        System.out.println("無効な番号です。");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("番号を正しく入力してください。");
                }

            } else if (input.startsWith("done ")) {
                try {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).done = true;
                        System.out.println("タスクを完了にしました: " + tasks.get(index).title);
                    } else {
                        System.out.println("無効な番号です。");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("番号を正しく入力してください。");
                }

            } else if (input.startsWith("edit ")) {
                try {
                    String[] parts = input.split(" ", 3);
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        String oldTitle = tasks.get(index).title;
                        tasks.get(index).title = parts[2];
                        System.out.println("タスクを更新しました: " + oldTitle + " -> " + parts[2]);
                    }

                } catch (Exception e) {
                    System.out.println("editコマンドは 'edit <番号> <新タイトル>' の形式です。");
                }

            } else if (input.startsWith("search ")) {
                String keyword = input.substring(7).toLowerCase();
                System.out.println("検索結果:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    if (task.title.toLowerCase().contains(keyword)) {
                        System.out.println((i + 1) + ": " + tasks.get(i));
                    }
                }
        

            } else {
                System.out.println("無効なコマンドです。");
            }
        }
    }

    private static void listTasks(List<Task> tasks, String mode) {
        System.out.println("タスク一覧:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (mode.equals("all") ||
                (mode.equals("done") && task.done) ||
                (mode.equals("undone") && !task.done)) {
                System.out.println((i + 1) + ": " + task);
            }
        }
    }

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

    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE)) return tasks;
        try (BufferedReader reader = Files.newBufferedReader(FILE, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    boolean done = parts[0].equals("1");
                    String title = parts[1];
                    tasks.add(new Task(title, done));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;

    }

}