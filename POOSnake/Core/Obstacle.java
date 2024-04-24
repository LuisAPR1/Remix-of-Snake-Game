package Core;

import Geometry.Poligono;
import Geometry.Ponto;

public class Obstacle {

    private Poligono obstacle;
    private Ponto position;
    private ObstacleType type;

    public enum ObstacleType {
        D, // Dinâmico
        S // Estático
    }

    public Obstacle(ObstacleType type, Poligono obstacle) {
        this.position = position;
        this.type = type;
        this.obstacle = obstacle;
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
            obstacle.rotacionar(angle, position);
        }
    }
}
