package Core;

/**
 * Enumeração que define os tipos de rasterização.
 * 
 * Os tipos de rasterização determinam como os elementos são renderizados.
 * Atualmente, existem dois tipos: Outlined (contornado) e Filled (preenchido).
 * 
 * Os valores da enumeração representam os tipos de rasterização disponíveis.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public enum RasterizationType {
    /**
     * Tipo de rasterização: Contornado.
     * Os elementos são renderizados com contornos.
     */
    O, // Outlined
    
    /**
     * Tipo de rasterização: Preenchido.
     * Os elementos são renderizados com preenchimento.
     */
    F // Filled
}
