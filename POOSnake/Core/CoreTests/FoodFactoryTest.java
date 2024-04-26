package Core.CoreTests;

import Core.Arena;
import Core.FoodCircle;
import Core.FoodFactory;
import Core.FoodSquare;
import Core.AbstractFood;
import Core.AbstractFood.FoodType;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodFactoryTest {

    @Test
    public void testCreateFoodCircle() {
        Arena arena = new Arena(200, 200);
        AbstractFood food = FoodFactory.createFood("circle", Color.RED, FoodType.C, arena, 5);

        assertTrue(food instanceof FoodCircle);
        assertEquals(Color.RED, food.getColor());
        assertEquals(FoodType.C, food.getType());
    }

    @Test
    public void testCreateFoodSquare() {
        Arena arena = new Arena(200, 200);
        AbstractFood food = FoodFactory.createFood("square", Color.YELLOW, FoodType.S, arena, 5);

        assertTrue(food instanceof FoodSquare);
        assertEquals(Color.YELLOW, food.getColor());
        assertEquals(FoodType.S, food.getType());
    }
}