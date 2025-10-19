package october2025.calculatorapp;

public enum Operation {

    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
