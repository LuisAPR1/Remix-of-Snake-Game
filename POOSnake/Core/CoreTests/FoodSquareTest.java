package Core.CoreTests;

import Core.Arena;
import Core.FoodSquare;
import Core.FoodType;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FoodSquareTest {

    @Test
    void testSpawnFood() {
        // Criar uma arena com dimensões pequenas para facilitar o teste
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);
        
        // Criar uma comida quadrada
        FoodSquare food = new FoodSquare(Color.RED, FoodType.S, arena, 2);

        // Verificar se a comida foi gerada corretamente
        assertNotNull(food.getPosition()); // A posição da comida não deve ser nula
        assertTrue(food.getShape().getPontos().size() == 4); // Deve haver 4 vértices no quadrado
    }

    @Test
    void testFoodInsideArena() {
        // Criar uma arena pequena para facilitar o teste
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);
        
        // Criar uma comida quadrada
        FoodSquare food = new FoodSquare(Color.RED, FoodType.S, arena, 2);

        // Verificar se a comida está dentro da arena
        assertTrue(food.getPosition().getX() >= 0 && food.getPosition().getX() < 10); // Coordenada X da comida deve estar entre 0 e 9
        assertTrue(food.getPosition().getY() >= 0 && food.getPosition().getY() < 10); // Coordenada Y da comida deve estar entre 0 e 9
    }
}
