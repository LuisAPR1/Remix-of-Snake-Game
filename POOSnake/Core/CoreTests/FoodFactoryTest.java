package Core.CoreTests;

import Core.AbstractFood;
import Core.Arena;
import Core.FoodCircle;
import Core.FoodFactory;
import Core.FoodSquare;
import Core.FoodType;
import Core.InterfaceMode;
import Core.RasterizationType;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodFactoryTest {

    @Test
    public void testCreateFoodCircle() {
        Ponto starter = new Ponto(10, 10);
        int arenaDimensionsX = 200;
        int arenaDimensionsY = 200;
        Arena arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 5, RasterizationType.O, 0, InterfaceMode.T);

        AbstractFood food = FoodFactory.createFood( Color.RED, FoodType.C, arena, 5);

        assertTrue(food instanceof FoodCircle);
        assertEquals(Color.RED, food.getColor());
        assertEquals(FoodType.C, food.getType());
    }

    @Test
    public void testCreateFoodSquare() {
        Ponto starter = new Ponto(10, 10);
        int arenaDimensionsX = 200;
        int arenaDimensionsY = 200;
        Arena arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 5, RasterizationType.O, 0, InterfaceMode.T);

        AbstractFood food = FoodFactory.createFood( Color.YELLOW, FoodType.S, arena, 5);

        assertTrue(food instanceof FoodSquare);
        assertEquals(Color.YELLOW, food.getColor());
        assertEquals(FoodType.S, food.getType());
    }
}