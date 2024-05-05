package Core;

public enum Cell {
    HEAD("H"),
    TAIL("T"),
    FOOD("F"),
    OBSTACLE("O"),
    EMPTY(".");

    private final String state;

    Cell(String state) {
        this.state = state;
    }

    public String getSymbol() {
        return state;
    }

    
}
