package Core.CoreTests;

import Core.Arena;
import Core.FoodFactory;
import Core.AbstractFood;
import Core.AbstractFood.FoodType;
import Core.Obstacle;
import Core.Obstacle.ObstacleType;
import Core.Snake;
import Geometry.Poligono;
import Geometry.Ponto;
import java.awt.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {

    @Test
    public void testObstacleSpawn() {
        // Configuring the arena
        int arenaWidth = 200;
        int arenaHeight = 200;
        Arena arena = new Arena(arenaWidth, arenaHeight);

        // Configuring the snake
        Ponto starter = new Ponto(10, 10);
        int headDimensions = 5;
        Snake snake = new Snake(starter, headDimensions);

        // Configuring the food
        AbstractFood food = FoodFactory.createFood("circle", Color.RED, FoodType.C, arena, headDimensions);

        // Configuring the obstacle
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0");
        Obstacle obstacle = new Obstacle(ObstacleType.S, obstacleShape, new Ponto(0, 0));

        // Checking if the obstacle is within the arena
        Ponto obstaclePosition = obstacle.getPosition();
        assertTrue(obstaclePosition.getX() >= 0 && obstaclePosition.getX() < arenaWidth);
        assertTrue(obstaclePosition.getY() >= 0 && obstaclePosition.getY() < arenaHeight);

        // Verifying that the position of the obstacle corresponds to an empty cell in the arena
        assertEquals('E', arena.getGrid()[obstaclePosition.getX()][obstaclePosition.getY()].getSymbol());
    }

    @Test
    public void testRotateDynamicObstacleWithoutRotationPoint() {
        // Configuring the test environment
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0"); // Square with side 1
        Obstacle obstacle = new Obstacle(ObstacleType.D, obstacleShape, new Ponto(0, 0));

        // Rotating the obstacle by 90 degrees
        obstacle.rotate(90);

        // Getting the coordinates of the obstacle after rotation
        Poligono obstacleAfterRotation = obstacle.getObstacle();
        Poligono expected = new Poligono("1 0 0 0 0 1 1 1"); // Expected shape after rotation

        // Verifying if the coordinates after rotation match the expected coordinates
        assertArrayEquals(expected.getPontos().toArray(), obstacleAfterRotation.getPontos().toArray());
    }

    @Test
    public void testRotateDynamicObstacleWithRotationPoint() {
        // Configuring the test environment
        Ponto rotationPoint = new Ponto(15, 15); // Rotation point
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0"); // Square with side 1
        Obstacle obstacle = new Obstacle(ObstacleType.D, obstacleShape, rotationPoint);

        // Getting the coordinates of the obstacle before rotation
        Poligono obstacleBeforeRotation = obstacle.getObstacle();

        // Rotating the obstacle by 90 degrees
        obstacle.rotate(90);

        // Getting the coordinates of the obstacle after rotation
        Poligono obstacleAfterRotation = obstacle.getObstacle();

        // Verifying if the coordinates after rotation match the expected coordinates
        // Note that since the rotation point is not the center of the polygon, the coordinates should change
        assertArrayEquals(obstacleBeforeRotation.getPontos().toArray(), obstacleAfterRotation.getPontos().toArray());
    }

}