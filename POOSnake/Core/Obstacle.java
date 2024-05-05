package Core;

import Geometry.Poligono;
import Geometry.Ponto;

public class Obstacle {

    private Poligono obstacle;
    private Ponto position;
    private ObstacleType type;
    private Ponto rotacao;

    public enum ObstacleType {
        D, // Dinâmico
        S // Estático
    }

    public Obstacle(ObstacleType d, Poligono obstacle, Ponto rotacao) {
        this.type = d;
        this.obstacle = obstacle;
        this.rotacao = rotacao;
        spawnObstacle(obstacle, d);
    }

    public void spawnObstacle(Poligono obstacle, ObstacleType type) {
        // invariancia nao pode spawnar fora da arena, dentro da cobra nem dentro do
        // obstaculo e mais pequeno do que a cabeça da cobra

        // TAMANHO DA FRUTA , (int) Math.random() % maxFoodSize

    }

    public Ponto getPosition() {
        return position;
    }

    public void setPosition(Ponto position) {
        this.position = position;
    }

    public ObstacleType getType() {
        return type;
    }

    public void setType(ObstacleType type) {
        this.type = type;
    }

    public void rotate(int angle) {
        if (this.type == ObstacleType.D) {
            if (rotacao == null) {
                obstacle.rotacionar(angle, obstacle.calcularCentro());
            } else {
                obstacle.rotacionar(angle, position);
            }
        }
    }

    public Poligono getObstacle() {
        return obstacle;
    }
}
