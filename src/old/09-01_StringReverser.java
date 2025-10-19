
class StringReverser {
    public static void main(String[] args) {
        String text = "Hello World!";
        String reversed = new StringBuilder(text).reverse().toString();
        System.out.println(reversed);
    }
}
