package Core;


import Geometry.Poligono;
import Geometry.Ponto;

public class Obstacle {

    private Poligono obstacle;
    private Ponto position;
    
    public void setObstacle(Poligono obstacle) {
        this.obstacle = obstacle;
    }

    private ObstacleType type;
    private Ponto rotacao;

    public enum ObstacleType {
        D, // Dinâmico
        S // Estático
    }

    public Obstacle(Obstacle.ObstacleType obstacleType, Poligono obstacle, Ponto rotacao) {
        this.type = obstacleType;
        this.obstacle = obstacle;
        this.rotacao = rotacao;
        
        spawnObstacle(obstacle, obstacleType);
    }

    public void spawnObstacle(Poligono obstacle, ObstacleType type) 
    {
    
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
