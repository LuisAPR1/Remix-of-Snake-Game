package Core.CoreTests;

import Core.Arena;
import Core.FoodCircle;
import Core.FoodType;
import Core.InterfaceMode;
import Core.RasterizationType;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodCircleTest {

    @Test
    public void testFoodCircleCreation() {
        Ponto starter = new Ponto(10, 10);
        int arenaDimensionsX = 200;
        int arenaDimensionsY = 200;
        Arena arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 5,
                RasterizationType.O, 0, InterfaceMode.T);

        FoodCircle foodCircle = new FoodCircle(Color.BLUE, FoodType.C, arena, 15);

        assertNotNull(foodCircle);
        assertEquals(Color.BLUE, foodCircle.getColor());
        assertEquals(FoodType.C, foodCircle.getType());
        assertNotNull(foodCircle.getPosition());
    }

    @Test
    public void testFoodCirclePositionWithinArena() {
        Ponto starter = new Ponto(10, 10);
        int arenaDimensionsX = 200;
        int arenaDimensionsY = 200;
        Arena arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 5,
                RasterizationType.O, 0, InterfaceMode.T);

        FoodCircle foodCircle = new FoodCircle(Color.BLUE, FoodType.C, arena, 15);
        Ponto position = foodCircle.getPosition();

        assertTrue(position.getX() >= 0 && position.getX() < 200);
        assertTrue(position.getY() >= 0 && position.getY() < 200);
    }
}