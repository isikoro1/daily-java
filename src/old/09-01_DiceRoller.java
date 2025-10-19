package Java._2025;

import java.util.Random;

class DiceRoller {
    public static void main(String[] args) {
        Random rand = new Random();
        int dice = rand.nextInt(6) + 1;
        System.out.println("サイコロの目: " + dice);
    }
}
