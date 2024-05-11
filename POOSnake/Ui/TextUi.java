package UI;

import Core.RasterizationStrategy;

/**
 * Classe que representa a interface de texto do usuário (UI) para renderização textual.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 */
public class TextUI implements UI {
    private RasterizationStrategy rasterizationStrategy;

    /**
     * Construtor da classe TextUI.
     * 
     * @param rasterizationStrategy estratégia de rasterização a ser utilizada.
     */
    public TextUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    /**
     * Método para renderizar a interface de texto.
     */
    @Override
    public void render() {
        rasterizationStrategy.render();
        System.out.println(toString());
        // Implementação adicional para renderização textual
    }

    /**
     * Retorna uma representação textual da grade de caracteres da estratégia de rasterização.
     * 
     * @return representação textual da grade.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rasterizationStrategy.getGrid().length; i++) {
            for (int j = 0; j < rasterizationStrategy.getGrid()[i].length; j++) {
                sb.append(rasterizationStrategy.getGrid()[i][j].getSymbol()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
