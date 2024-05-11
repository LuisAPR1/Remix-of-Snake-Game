package Core;

import Geometry.Poligono;
import Geometry.Ponto;

/**
 * Classe que representa um obstáculo na arena.
 * 
 * Um obstáculo pode ser estático ou dinâmico, e é definido por um polígono e uma posição.
 * A rotação de um obstáculo é suportada apenas se ele for dinâmico.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public class Obstacle {

    // Polígono que define a forma do obstáculo
    private Poligono obstacle;
    // Posição do obstáculo
    private Ponto position;
    // Tipo do obstáculo (estático ou dinâmico)
    private ObstacleType type;
    // Ponto de rotação (aplicável apenas a obstáculos dinâmicos)
    private Ponto rotacao;

    /**
     * Enumeração que define o tipo de obstáculo.
     */
    public enum ObstacleType {
    /**
     * Obstaculo dinâmico.
     */
    D,

    /**
     * Obstaculo estático.
     */
    S
    }

    /**
     * Construtor para criar um obstáculo.
     * 
     * @param obstacleType O tipo de obstáculo (estático ou dinâmico).
     * @param obstacle     O polígono que define a forma do obstáculo.
     * @param rotacao      O ponto de rotação do obstáculo (aplicável apenas a obstáculos dinâmicos).
     */
    public Obstacle(Obstacle.ObstacleType obstacleType, Poligono obstacle, Ponto rotacao) {
        this.type = obstacleType;
        this.obstacle = obstacle;
        this.rotacao = rotacao;
        
        spawnObstacle(obstacle, obstacleType);
    }

    /**
     * Método para fazer com que o obstáculo apareça na arena.
     * 
     * @param obstacle O obstáculo a ser colocado na arena.
     * @param type     O tipo de obstáculo.
     */
    public void spawnObstacle(Poligono obstacle, ObstacleType type) 
    {
        // Lógica para fazer o obstáculo aparecer na arena
    }   

    /**
     * Obtém a posição do obstáculo.
     * 
     * @return A posição atual do obstáculo.
     */
    public Ponto getPosition() {
        return position;
    }

    /**
     * Define a posição do obstáculo.
     * 
     * @param position A nova posição do obstáculo.
     */
    public void setPosition(Ponto position) {
        this.position = position;
    }

    /**
     * Obtém o tipo do obstáculo.
     * 
     * @return O tipo do obstáculo (estático ou dinâmico).
     */
    public ObstacleType getType() {
        return type;
    }

    /**
     * Define o tipo do obstáculo.
     * 
     * @param type O novo tipo do obstáculo.
     */
    public void setType(ObstacleType type) {
        this.type = type;
    }

    /**
     * Rotaciona o obstáculo em um determinado ângulo.
     * 
     * @param angle O ângulo de rotação em graus.
     * @return O polígono do obstáculo após a rotação.
     */
    public Poligono rotate(int angle) {
        if (this.type == ObstacleType.D) {

            Poligono a;
            if (rotacao == null) {
                a= obstacle.rotacionar(angle, obstacle.calcularCentro());
            } else {
                a= obstacle.rotacionar(angle, rotacao);
            }
            return a;
        }
        return null;
        
    }

    /**
     * Define o poligono do obstáculo.
     * 
     * @param obstacle O novo poligono que irá representar o obstáculo.
     */
    public void setObstacle(Poligono obstacle) {
        this.obstacle = obstacle;
    }

    /**
     * Obtém o polígono que define a forma do obstáculo.
     * 
     * @return O polígono que define a forma do obstáculo.
     */
    public Poligono getObstacle() {
        return obstacle;
    }
}
