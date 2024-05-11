package Core;

/**
 * Interface que define uma estratégia de rasterização.
 * 
 * Uma estratégia de rasterização é responsável por renderizar elementos em uma grade de células.
 * As classes que implementam esta interface devem fornecer métodos para obter a grade de células e renderizar elementos nela.
 * 
 * A grade de células é representada por uma matriz de células.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public interface RasterizationStrategy {

    /**
     * Obtém a grade de células.
     * 
     * @return A matriz de células que representa a grade.
     */
    Cell[][] getGrid();
   
    /**
     * Renderiza elementos na grade de células.
     * 
     * Este método deve ser implementado pelas classes que utilizam esta interface para definir como os elementos serão renderizados na grade.
     */
    void render();   
}
