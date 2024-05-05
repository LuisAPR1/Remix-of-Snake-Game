package Core.CoreTests;

import Core.Arena;
import Core.FoodSquare;
import Core.FoodType;
import Core.InterfaceMode;
import Core.RasterizationType;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodSquareTest {

    @Test
    public void testFoodSquareCreation() {
        Ponto starter = new Ponto(10, 10);
        int arenaDimensionsX = 200;
        int arenaDimensionsY = 200;
        Arena arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 5, RasterizationType.O, 0, InterfaceMode.T);

        FoodSquare foodSquare = new FoodSquare(Color.GREEN, FoodType.S, arena, 10);

        assertNotNull(foodSquare);
        assertEquals(Color.GREEN, foodSquare.getColor());
        assertEquals(FoodType.S, foodSquare.getType());
        assertNotNull(foodSquare.getPosition());
    }

    @Test
    public void testFoodSquarePositionWithinArena() {
        Ponto starter = new Ponto(10, 10);
        int arenaDimensionsX = 200;
        int arenaDimensionsY = 200;
        Arena arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 5, RasterizationType.O, 0, InterfaceMode.T);

        FoodSquare foodSquare = new FoodSquare(Color.GREEN, FoodType.S, arena, 10);
        Ponto position = foodSquare.getPosition();

        assertTrue(position.getX() >= 0 && position.getX() < 200);
        assertTrue(position.getY() >= 0 && position.getY() < 200);
    }
}