package Core.CoreTests;

import Core.Arena;
import Core.FoodCircle;
import Core.FoodSquare;
import Core.FoodType;
import Core.InterfaceMode;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodCircleTest {

    @Test
    void testSpawnFood() {
        // Criar uma arena com dimensões pequenas para facilitar o teste
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M');
        
        // Criar uma comida circular
        FoodCircle food = new FoodCircle(Color.RED, FoodType.C, arena, 2);

        // Verificar se a comida foi gerada corretamente
        assertNotNull(food.getPosition()); // A posição da comida não deve ser nula
        assertTrue(food.getShape().getRaio() == 1); // O raio do círculo deve ser metade do diâmetro fornecido (2/2 = 1)
    }

    @Test
    void testFoodInsideArena() {
        // Criar uma arena pequena para facilitar o teste
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M');
        
        // Criar uma comida quadrada
        FoodSquare food = new FoodSquare(Color.RED, FoodType.S, arena, 2);

        // Verificar se a comida está dentro da arena
        assertTrue(food.getPosition().getX() >= 0 && food.getPosition().getX() < 10); 
        assertTrue(food.getPosition().getY() >= 0 && food.getPosition().getY() < 10);
    }
}