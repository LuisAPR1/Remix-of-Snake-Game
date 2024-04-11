public enum Cell {
    HEAD("H"),
    TAIL("T"),
    FOOD("F"),
    OBSTACLE("O"),
    EMPTY("E");

    private final String symbol;

    Cell(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
