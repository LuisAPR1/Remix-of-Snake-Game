package Core.CoreTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Geometry.Poligono;
import Geometry.Ponto;
import Core.Obstacle;

public class ObstacleTest {

    @Test
    public void testRotateDynamicObstacle1() {
        Poligono obstaclePolygon = new Poligono("0 0 0 4 4 4 4 0");
        
        Ponto rotationPoint = new Ponto(2, 2);

        Obstacle obstacle = new Obstacle(Obstacle.ObstacleType.D, obstaclePolygon, rotationPoint);
        Poligono rotatedObstacle = obstacle.rotate(90);

        assertEquals(4, rotatedObstacle.getPontos().size());
        assertEquals(new Ponto(4, 0), rotatedObstacle.getPontos().get(0));
        assertEquals(new Ponto(4, 4), rotatedObstacle.getPontos().get(1));
        assertEquals(new Ponto(0, 4), rotatedObstacle.getPontos().get(2));
        assertEquals(new Ponto(0, 0), rotatedObstacle.getPontos().get(3));
    }

    @Test
    public void testRotateDynamicObstacle2() {
        Poligono obstaclePolygon = new Poligono("0 0 0 3 3 3 3 0");

        Ponto rotationPoint = new Ponto(1.5, 1.5);

        Obstacle obstacle = new Obstacle(Obstacle.ObstacleType.D, obstaclePolygon, rotationPoint);
        Poligono rotatedObstacle = obstacle.rotate(90);

        assertEquals(4, rotatedObstacle.getPontos().size());
        assertEquals(new Ponto(3, 0), rotatedObstacle.getPontos().get(0));
        assertEquals(new Ponto(3, 3), rotatedObstacle.getPontos().get(1));
        assertEquals(new Ponto(0, 3), rotatedObstacle.getPontos().get(2));
        assertEquals(new Ponto(0, 0), rotatedObstacle.getPontos().get(3));
    }
}
