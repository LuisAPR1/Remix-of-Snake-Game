package UI;

import Core.RasterizationStrategy;

/**
 * Classe responsável por criar instâncias de interfaces de usuário (UI) com base no tipo especificado.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 */
public class UIFactory {
    /**
     * Método estático para criar uma instância de UI com base no tipo especificado.
     * 
     * @param type                tipo de UI ('t' para textual, 'g' para gráfica).
     * @param rasterizationStrategy estratégia de rasterização a ser utilizada.
     * @return instância de UI correspondente ao tipo especificado.
     * @throws IllegalArgumentException se o tipo de UI especificado não for válido.
     */
    public static UI createUI(char type, RasterizationStrategy rasterizationStrategy) {
        if (Character.toLowerCase(type) == 't') {
            return new TextUI(rasterizationStrategy);
        } else if (Character.toLowerCase(type) == 'g') {
            return new GraphicalUI(rasterizationStrategy);
        } else {
            throw new IllegalArgumentException("Tipo de UI inválido.");
        }
    }
}
