package Core.CoreTests;

import Core.AbstractFood;
import Core.Arena;
import Core.FoodCircle;
import Core.FoodFactory;
import Core.FoodSquare;
import Core.FoodType;
import Core.InterfaceMode;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodFactoryTest {

    @Test
    public void testCreateFoodCircle() {
        // Criar uma arena fictícia
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0);

        // Criar uma comida circular usando a FoodFactory
        AbstractFood<?> food = FoodFactory.createFood(Color.RED, FoodType.C, arena, 2);

        // Verificar se a instância retornada não é nula e é uma instância de FoodCircle
        assertNotNull(food);
        assertTrue(food instanceof FoodCircle);
    }

    @Test
    void testCreateFoodSquare() {
        // Criar uma arena fictícia
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0);

        // Criar uma comida quadrada usando a FoodFactory
        AbstractFood<?> food = FoodFactory.createFood(Color.RED, FoodType.S, arena, 2);

        // Verificar se a instância retornada não é nula e é uma instância de FoodSquare
        assertNotNull(food);
        assertTrue(food instanceof FoodSquare);
    }
}