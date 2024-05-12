package Core.CoreTests;

import Core.Arena;
import Core.FoodCircle;
import Core.FoodSquare;
import Core.FoodType;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodCircleTest {

    @Test
    void testSpawnFood() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);
        
        FoodCircle food = new FoodCircle(Color.RED, FoodType.C, arena, 2);

    
        assertNotNull(food.getPosition()); 
        assertTrue(food.getShape().getRaio() == 1); 
    }

    @Test
    void testFoodInsideArena() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);
        
        FoodSquare food = new FoodSquare(Color.RED, FoodType.S, arena, 2);

        
        assertTrue(food.getPosition().getX() >= 0 && food.getPosition().getX() < 10); 
        assertTrue(food.getPosition().getY() >= 0 && food.getPosition().getY() < 10);
    }
}