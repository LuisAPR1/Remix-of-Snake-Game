package Core.CoreTests;

import Core.Arena;
import Core.FoodCircle;
import Core.AbstractFood.FoodType;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodCircleTest {

    @Test
    public void testFoodCircleCreation() {
        Arena arena = new Arena(200, 200);
        FoodCircle foodCircle = new FoodCircle(Color.BLUE, FoodType.C, arena, 15);

        assertNotNull(foodCircle);
        assertEquals(Color.BLUE, foodCircle.getColor());
        assertEquals(FoodType.C, foodCircle.getType());
        assertNotNull(foodCircle.getPosition());
    }

    @Test
    public void testFoodCirclePositionWithinArena() {
        Arena arena = new Arena(200, 200);
        FoodCircle foodCircle = new FoodCircle(Color.BLUE, FoodType.C, arena, 15);
        Ponto position = foodCircle.getPosition();

        assertTrue(position.getX() >= 0 && position.getX() < 200);
        assertTrue(position.getY() >= 0 && position.getY() < 200);
    }
}