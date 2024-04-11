package Arena;

public class Obstacle {
    private Ponto position;
    private ObstacleType type;

    public enum ObstacleType {
        D, // Dinâmico
        S  // Estático
    }

    public Obstacle(Ponto position, ObstacleType type) {
        this.position = position;
        this.type = type;
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
}
