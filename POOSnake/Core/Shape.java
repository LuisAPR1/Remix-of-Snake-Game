package Core;

import java.util.List;

import Geometry.Ponto;

/**
 * Interface que define um shape (forma geométrica).
 * 
 * Um shape é uma entidade geométrica que pode ser renderizada e manipulada em um ambiente gráfico.
 * Esta interface define métodos para obter todas as coordenadas do shape e obter sua posição.
 * 
 * As classes que implementam esta interface devem fornecer implementações para esses métodos.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public interface Shape {
    
    /**
     * Obtém todas as coordenadas do shape.
     * 
     * @return Uma lista de pontos representando todas as coordenadas do shape.
     */
    List<Ponto> getAllCoordinates();

    /**
     * Obtém a posição do shape.
     * 
     * @return O ponto que representa a posição do shape.
     */
    Ponto getPosition();
}
