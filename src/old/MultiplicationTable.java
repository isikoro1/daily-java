package Java._2025;

class MultiplicationTable {
    public static void main(String[] args) {
        System.out.println("=== 九九表 ===");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%2d ", i * j);
            }
            System.out.println();
        }
    }
}