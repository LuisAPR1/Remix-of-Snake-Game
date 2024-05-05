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

public class AbstractFoodTest {

    @Test
    public void testFoodSpawn() {
        

        Ponto starter = new Ponto(10, 10);
        int arenaDimensionsX = 200;
        int arenaDimensionsY = 200;
        Arena arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 5, RasterizationType.O, 0, InterfaceMode.T);

        
        int headDimensions = 5;
        @SuppressWarnings("unused")
        Snake snake = new Snake(starter, headDimensions);        
        
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0");
        @SuppressWarnings("unused")
        Obstacle obstacle = new Obstacle(ObstacleType.S, obstacleShape, null);

        AbstractFood food = FoodFactory.createFood(Color.RED, FoodType.C, arena, headDimensions);
        Ponto foodPosition = food.getPosition();

        assertTrue(foodPosition.getX() >= 0 && foodPosition.getX() < arenaDimensionsX);
        assertTrue(foodPosition.getY() >= 0 && foodPosition.getY() < arenaDimensionsY);

        assertEquals('E', arena.getGrid()[foodPosition.getX()][foodPosition.getY()].getSymbol());
    }
}