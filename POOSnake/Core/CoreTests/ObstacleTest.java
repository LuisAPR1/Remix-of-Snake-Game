package Core.CoreTests;

import Core.AbstractFood;
import Core.Arena;
import Core.FoodFactory;
import Core.FoodType;
import Core.InterfaceMode;
import Core.Obstacle;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import Core.Snake;
import Geometry.Poligono;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ObstacleTest {

    @Test
    public void testObstacleSpawn() {
        int arenaWidth = 200;
        int arenaHeight = 200;
        int arenaDimensionsX = 200;
        int arenaDimensionsY = 200;
        Ponto starter = new Ponto(10, 10);
        Arena arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 5, RasterizationType.O, 0, InterfaceMode.T);


        int headDimensions = 5;
        @SuppressWarnings("unused")
        Snake snake = new Snake(starter, headDimensions);

        @SuppressWarnings("unused")
        AbstractFood food = FoodFactory.createFood(Color.RED, FoodType.C, arena, headDimensions);

        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0");
        Obstacle obstacle = new Obstacle(ObstacleType.S, obstacleShape,null);

        Ponto obstaclePosition = obstacle.getPosition();
        assertTrue(obstaclePosition.getX() >= 0 && obstaclePosition.getX() < arenaWidth);
        assertTrue(obstaclePosition.getY() >= 0 && obstaclePosition.getY() < arenaHeight);

        assertEquals('E', arena.getGrid()[obstaclePosition.getX()][obstaclePosition.getY()].getSymbol());
    }

    @Test
    public void testRotateDynamicObstacleWithoutRotationPoint() {
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0"); 
        Obstacle obstacle = new Obstacle(ObstacleType.D, obstacleShape, null);

        obstacle.rotate(90);

        Poligono obstacleAfterRotation = obstacle.getObstacle();
        Poligono expected = new Poligono("1 0 0 0 0 1 1 1");

        assertArrayEquals(expected.getPontos().toArray(), obstacleAfterRotation.getPontos().toArray());
    }

    @Test
    public void testRotateDynamicObstacleWithRotationPoint() {
        Ponto rotationPoint = new Ponto(1, 1); 
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0"); 
        Obstacle obstacle = new Obstacle(ObstacleType.D, obstacleShape, rotationPoint);

        obstacle.rotate(180);

        Poligono obstacleAfterRotation = obstacle.getObstacle();
        Poligono expected = new Poligono("2 2 1 2 1 1 2 1");

      
        assertArrayEquals(expected.getPontos().toArray(), obstacleAfterRotation.getPontos().toArray());
    }
    
}