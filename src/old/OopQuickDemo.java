package Java._2025;

class Animal {
    String name;

    // コンストラクタ
    Animal(String name) {
        this.name = name;
    }

    // デフォルトの鳴き方
    void speak() {
        System.out.println(name + " は何かを言いたそうだ...");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name); // 親クラスのコンストラクタを呼び出す
    }

    @Override
    void speak() {
        System.out.println(name + ": ワン！");
    }
}

class Cat extends Animal {
    Cat(String name) {
        super(name);
    }

    @Override
    void speak() {
        System.out.println(name + ": ニャー！");
    }
}

public class OopQuickDemo {
    public static void main(String[] args) {
        Animal[] animals = {
                new Dog("ポチ"),
                new Cat("タマ"),
                new Dog("シロ")
        };

        for (Animal a : animals) {
            a.speak();
        }
    }
}