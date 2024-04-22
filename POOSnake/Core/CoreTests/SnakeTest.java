package Core.CoreTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
    @Test
    public void testMove() {
        Snake snake = new Snake();
        // Testando se a snake move corretamente
        snake.move();
        // Adicione mais verificações aqui, dependendo da lógica de movimento
        // por exemplo, você pode verificar se a posição da snake foi atualizada corretamente
        // ou se ela não saiu dos limites do jogo
    }

    @Test
    public void testGrow() {
        Snake snake = new Snake();
        int initialSize = snake.getSize();
        snake.grow();
        // Testando se o tamanho da snake aumentou após comer
        assertEquals(initialSize + 1, snake.getSize());
    }

    @Test
    public void testChangeDirection() {
        Snake snake = new Snake();
        // Testando se a direção da snake é alterada corretamente
        snake.changeDirection(Snake.UP);
        assertEquals(Snake.UP, snake.getDirection());
        snake.changeDirection(Snake.DOWN);
        assertEquals(Snake.DOWN, snake.getDirection());
        snake.changeDirection(Snake.LEFT);
        assertEquals(Snake.LEFT, snake.getDirection());
        snake.changeDirection(Snake.RIGHT);
        assertEquals(Snake.RIGHT, snake.getDirection());
        // Adicione mais verificações aqui se necessário, dependendo das direções permitidas
    }
}
