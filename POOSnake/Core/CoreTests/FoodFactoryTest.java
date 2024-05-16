package Core.CoreTests;

import Core.AbstractFood;
import Core.Arena;
import Core.FoodCircle;
import Core.FoodFactory;
import Core.FoodSquare;
import Core.FoodType;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodFactoryTest {

    @Test
    public void testCreateFoodCircle() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);

        AbstractFood<?> food = FoodFactory.createFood(Color.RED, FoodType.C, arena, 2);

    
        assertNotNull(food);
        assertTrue(food instanceof FoodCircle);
    }

    @Test
    void testCreateFoodSquare() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);

        AbstractFood<?> food = FoodFactory.createFood(Color.RED, FoodType.S, arena, 2);

        
        assertNotNull(food);
        assertTrue(food instanceof FoodSquare);
    }
}