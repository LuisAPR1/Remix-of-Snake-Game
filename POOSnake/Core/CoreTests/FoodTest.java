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

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    @Test
    public void testFoodSpawn() {
        // Configuring the arena
        int arenaWidth = 200;
        int arenaHeight = 200;

        Arena arena = new Arena(arenaWidth, arenaHeight);

        Ponto starter = new Ponto(10, 10);
        int headDimensions = 5;
        @SuppressWarnings("unused");
        Snake snake = new Snake(starter, headDimensions);        
        
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0");
        Obstacle obstacle = new Obstacle(ObstacleType.S, obstacleShape, null);

        // Configuring the food using the factory
        AbstractFood food = FoodFactory.createFood("circle", Color.RED, FoodType.C, arena, headDimensions);
        Ponto foodPosition = food.getPosition();

        // Asserting that the food is within the arena bounds
        assertTrue(foodPosition.getX() >= 0 && foodPosition.getX() < arenaWidth);
        assertTrue(foodPosition.getY() >= 0 && foodPosition.getY() < arenaHeight);

        // Verifying that the food's position corresponds to an empty cell in the arena
        assertEquals('E', arena.getGrid()[foodPosition.getX()][foodPosition.getY()].getSymbol());
    }
}