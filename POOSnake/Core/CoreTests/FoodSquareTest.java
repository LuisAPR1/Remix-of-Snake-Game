package Core.CoreTests;

import Core.Arena;
import Core.FoodSquare;
import Core.AbstractFood.FoodType;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodSquareTest {

    @Test
    public void testFoodSquareCreation() {
        Arena arena = new Arena(200, 200);
        FoodSquare foodSquare = new FoodSquare(Color.GREEN, FoodType.S, arena, 10);

        assertNotNull(foodSquare);
        assertEquals(Color.GREEN, foodSquare.getColor());
        assertEquals(FoodType.S, foodSquare.getType());
        assertNotNull(foodSquare.getPosition());
    }

    @Test
    public void testFoodSquarePositionWithinArena() {
        Arena arena = new Arena(200, 200);
        FoodSquare foodSquare = new FoodSquare(Color.GREEN, FoodType.S, arena, 10);
        Ponto position = foodSquare.getPosition();

        assertTrue(position.getX() >= 0 && position.getX() < 200);
        assertTrue(position.getY() >= 0 && position.getY() < 200);
    }
}