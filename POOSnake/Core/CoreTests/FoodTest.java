package Core.CoreTests;
import Core.Arena;
import Core.Food;
import Core.Food.FoodType;
import Core.Obstacle;
import Core.Obstacle.ObstacleType;
import Core.Snake;
import Geometry.Poligono;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    public void testFoodSpawn() {
        // Configurando a arena
        int arenaWidth = 200;
        int arenaHeight = 200;

        Arena arena = new Arena(arenaWidth, arenaHeight);

        Ponto starter = new Ponto(10, 10);
        int headDimensions = 5;
        Snake snake = new Snake(starter, headDimensions);        
        
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0");
        Obstacle obstacle = new Obstacle(ObstacleType.S, obstacleShape, null);

        // Configurando a comida
        int[] arenaDimensions = {arenaWidth, arenaHeight};

        Food food = new Food(Color.RED, FoodType.C, arena, headDimensions);
        Ponto foodPosition = food.getPosition();

        // Verificando se a comida está dentro da arena
        assertTrue(foodPosition.getX() >= 0 && foodPosition.getX() < arenaDimensions[0]);
        assertTrue(foodPosition.getY() >= 0 && foodPosition.getY() < arenaDimensions[1]);

        // Verificando se a posição da comida corresponde a uma célula vazia na arena
        assertEquals('E', arena.getGrid()[foodPosition.getX()][foodPosition.getY()].getSymbol());
    }

}

