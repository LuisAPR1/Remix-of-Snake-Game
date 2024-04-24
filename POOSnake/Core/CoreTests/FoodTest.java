package Core.CoreTests;
import Core.Arena;
import Core.Food;
import Core.Food.FoodType;
import Core.Obstacle;
import Core.Obstacle.ObstacleType;
import Core.Snake;
import Geometry.Poligono;
import Geometry.Ponto;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    @Test
    public void testConstructorAndGetters() {
        // Criando uma instância de Food
        Ponto position = new Ponto(10, 20);
        Color color = Color.RED;
        FoodType type = FoodType.C;
        Food food = new Food(position, color, type);

        // Verificando se os valores estão corretos após a criação
        assertEquals(position, food.getPosition());
        assertEquals(color, food.getColor());
        assertEquals(type, food.getType());
    }

    @Test
    public void testSetters() {
        // Criando uma instância de Food
        Ponto position = new Ponto(10, 20);
        Color color = Color.RED;
        FoodType type = FoodType.C;
        Food food = new Food(position, color, type);

        // Modificando os valores
        Ponto newPosition = new Ponto(30, 40);
        Color newColor = Color.BLUE;
        FoodType newType = FoodType.S;

        food.setPosition(newPosition);
        food.setColor(newColor);
        food.setType(newType);

        // Verificando se os valores foram modificados corretamente
        assertEquals(newPosition, food.getPosition());
        assertEquals(newColor, food.getColor());
        assertEquals(newType, food.getType());
    }

    public void testFoodSpawn() {
        // Configurando a arena
        int arenaWidth = 200;
        int arenaHeight = 200;

        Arena arena = new Arena(arenaWidth, arenaHeight);

        Ponto starter = new Ponto(10, 10);
        int headDimensions = 5;
        Snake snake = new Snake(starter, headDimensions);


        
        
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0");
        Obstacle obstacle = new Obstacle(ObstacleType.S, obstacleShape);

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

