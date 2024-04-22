package Core.CoreTests;
import Core.Food;
import Core.Food.FoodType;
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
}

