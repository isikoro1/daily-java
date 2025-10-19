package Java._2025;

import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

public class AddressBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        while (true) {
            System.out.println("=== アドレス帳 ===");
            System.out.println("1: 追加");
            System.out.println("2: 一覧表示");
            System.out.println("3: 削除");
            System.out.println("0: 終了");
            System.out.print("番号を選んでください: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                System.out.println("終了します。");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("名前: ");
                    String name = sc.nextLine();
                    System.out.print("電話番号: ");
                    String phone = sc.nextLine();
                    contacts.add(new Contact(name, phone));
                    System.out.println("登録しました！");
                    break;
                case 2:
                    System.out.println("--- 登録一覧 ---");
                    for (int i = 0; i < contacts.size(); i++) {
                        Contact c = contacts.get(i);
                        System.out.printf("%d: %s (%s)%n", i, c.name, c.phone);
                    }
                    break;
                case 3:
                    System.out.print("削除する番号: ");
                    int idx = sc.nextInt();
                    if (idx >= 0 && idx < contacts.size()) {
                        contacts.remove(idx);
                        System.out.println("削除しました！");
                    } else {
                        System.out.println("無効な選択です");
                    }
                    break;
                default:
                    System.out.println("無効な選択です");

            }
            System.out.println();

        }
    }
}
