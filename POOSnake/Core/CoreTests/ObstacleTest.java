package Core.CoreTests;
import Core.Obstacle;
import Core.Obstacle.ObstacleType;
import Geometry.Ponto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {

    @Test
    public void testConstructorAndGetters() {
        // Criando uma instância de Obstacle
        Ponto position = new Ponto(10, 20);
        ObstacleType type = ObstacleType.D;
        Obstacle obstacle = new Obstacle(position, type);

        // Verificando se os valores estão corretos após a criação
        assertEquals(position, obstacle.getPosition());
        assertEquals(type, obstacle.getType());
    }

    @Test
    public void testSetters() {
        // Criando uma instância de Obstacle
        Ponto position = new Ponto(10, 20);
        ObstacleType type = ObstacleType.D;
        Obstacle obstacle = new Obstacle(position, type);

        // Modificando os valores
        Ponto newPosition = new Ponto(30, 40);
        ObstacleType newType = ObstacleType.S;

        obstacle.setPosition(newPosition);
        obstacle.setType(newType);

        // Verificando se os valores foram modificados corretamente
        assertEquals(newPosition, obstacle.getPosition());
        assertEquals(newType, obstacle.getType());
    }
}

