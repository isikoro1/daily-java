package october2025.calculatorapp;

public class Calculator {
    public double calculate(double a, double b, Operation op) {
        switch (op) {
            case ADD:
                return a + b;
            case SUB:
                return a - b;
            case MUL:
                return a * b;
            case DIV:
                if (b == 0)
                    throw new IllegalArgumentException("0で割ることはできません");
                return a / b;
            default:
                throw new UnsupportedOperationException("未対応の演算: " + op);
        }
    }
}
