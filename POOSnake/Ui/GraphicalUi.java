package UI;

import Core.RasterizationStrategy;

/**
 * Classe que representa a interface gráfica do usuário (UI) para renderização gráfica.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 */
public class GraphicalUI implements UI {
    private RasterizationStrategy rasterizationStrategy;

    /**
     * Construtor da classe GraphicalUI.
     * 
     * @param rasterizationStrategy estratégia de rasterização a ser utilizada.
     */
    public GraphicalUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    /**
     * Método para renderizar a interface gráfica.
     */
    @Override
    public void render() {
        rasterizationStrategy.render();
    }
}
