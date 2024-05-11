package Core;

/**
 * Enumeração que representa os possíveis estados de uma célula na arena do
 * jogo.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public enum Cell {
    HEAD("H"), // Cabeça da cobra
    TAIL("T"), // Corpo da cobra
    FOOD("F"), // Comida
    OBSTACLE("O"), // Obstáculo
    EMPTY("."); // Célula vazia

    private final String state;

    /**
     * Construtor privado para associar um símbolo a cada estado da célula.
     * 
     * @param state O símbolo que representa o estado da célula.
     */
    Cell(String state) {
        this.state = state;
    }

    /**
     * Obtém o símbolo associado ao estado da célula.
     * 
     * @return O símbolo que representa o estado da célula.
     */
    public String getSymbol() {
        return state;
    }
}
